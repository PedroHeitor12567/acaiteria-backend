package com.acaiteria.acaiteria_backend.adapters.in.controller;

import com.acaiteria.acaiteria_backend.domain.exception.RecursoNaoEncontradoException;
import com.acaiteria.acaiteria_backend.domain.exception.RegraDeNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RegraDeNegocioException.class)
    public ProblemDetail handleRegraDeNegocio(RegraDeNegocioException ex) {
        var problem = ProblemDetail.forStatusAndDetail(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
        problem.setTitle("Regra de Negócio Violada");
        return problem;
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ProblemDetail handleNaoEncontrado(RecursoNaoEncontradoException ex) {
        var problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problem.setTitle("Recurso Não Encontrado");
        return problem;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidacao(MethodArgumentNotValidException ex) {
        Map<String, String> erros = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        fe -> fe.getDefaultMessage() != null ? fe.getDefaultMessage() : "inválido"
                ));
        var problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setTitle("Erro de Validação");
        problem.setDetail("Um ou mais campos estão inválidos.");
        problem.setProperty("erros", erros);
        return problem;
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ProblemDetail handleCredenciais(BadCredentialsException ex) {
        var problem = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, "Email ou senha incorretos.");
        problem.setTitle("Autenticação Falhou");
        return problem;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGenerico(Exception ex) {
        var problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Ocorreu um erro inesperado. Tente novamente."
        );
        problem.setTitle("Erro Interno");
        return problem;
    }
}
