package com.acaiteria.acaiteria_backend.adapters.in.controller;

import com.acaiteria.acaiteria_backend.application.ports.in.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class TransacaoController {

    private final CriarTransacaoUseCase criarTransacaoUseCase;
    private final ListarTransacoesUseCase listarTransacoesUseCase;
    private final ExcluirTransacaoUseCase excluirTransacaoUseCase;
    private final CalcularLucroMensalUseCase calcularLucroMensalUseCase;
    private final SicronizarBancoDoBrasilUseCase sincronizarBBUseCase;

    public TransacaoController(
            CriarTransacaoUseCase criarTransacaoUseCase,
            ListarTransacoesUseCase listarTransacoesUseCase,
            ExcluirTransacaoUseCase excluirTransacaoUseCase,
            CalcularLucroMensalUseCase calcularLucroMensalUseCase,
            SicronizarBancoDoBrasilUseCase sincronizarBBUseCase
    ) {
        this.criarTransacaoUseCase = criarTransacaoUseCase;
        this.listarTransacoesUseCase = listarTransacoesUseCase;
        this.excluirTransacaoUseCase = excluirTransacaoUseCase;
        this.calcularLucroMensalUseCase = calcularLucroMensalUseCase;
        this.sincronizarBBUseCase = sincronizarBBUseCase;
    }

    @PostMapping("/transacoes")
    public ResponseEntity<TransacaoResponse> criar(@Valid @RequestBody CriarTransacaoRequest request){
        var command = new CriarTransacaoUseCase.Command(
                request.descricao(),
                request.valor(),
                request.tipo(),
                request.origem(),
                request.data()
        );
        var transacao = criarTransacaoUseCase.executar(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(TransacaoResponse.from(transacao));
    }

    @GetMapping("/transacoes")
    public ResponseEntity<List<TransacaoResponse>> listar(
            @RequestParam(required = false) Integer mes,
            @RequestParam(required = false) Integer ano
    ) {
        var query = new ListarTransacoesUseCase.Query(mes, ano);
        var transacoes = listarTransacoesUseCase.executar(query)
                .stream()
                .map(TransacaoResponse::from)
                .toList();
        return ResponseEntity.ok(transacoes);
    }

    @DeleteMapping("/transacoes/{id}")
    public ResponseEntity<Void> excluir(@PathVariable UUID id) {
        excluirTransacaoUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/relatorio/mensal")
    public ResponseEntity<ResumoMensalResponse> resumoMensal(
            @RequestParam int mes,
            @RequestParam int ano
    ) {
        var query = new CalcularLucroMensalUseCase.Query(mes, ano);
        var resumo = calcularLucroMensalUseCase.executar(query);
        return ResponseEntity.ok(ResumoMensalResponse.from(resumo));
    }

    @PostMapping("/sincronizar/banco-do-brasil")
    public ResponseEntity<String> sincronizarBB() {
        int importadas = sincronizarBBUseCase.executar();
        return ResponseEntity.ok("Sincronização concluída. %d transações importadas.".formatted(importadas));
    }
}
