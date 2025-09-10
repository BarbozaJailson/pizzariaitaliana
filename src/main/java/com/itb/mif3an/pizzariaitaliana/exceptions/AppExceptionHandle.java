package com.itb.mif3an.pizzariaitaliana.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RestControllerAdvice
@Slf4j
public class AppExceptionHandle {

    private final ZoneId zoneBrasil = ZoneId.of("America/Sao_Paulo");

    // Erro 404 (Url mal formada pelo front)
    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<Object> handleBadRequest(BadRequest ex){
        return buildErrorResponse(ex, HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    // Erro 404 (Url mal formada pelo front)
    @ExceptionHandler(NotFound.class)
    public ResponseEntity<Object> handleNotFound(NotFound ex){
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND, ex.getMessage());
    }

    // Erro 403 (Acesso proibido)
    @ExceptionHandler(Forbidden.class)
    public ResponseEntity<Object> handleForbidden(Forbidden ex){
        return buildErrorResponse(ex, HttpStatus.FORBIDDEN, ex.getMessage());
    }

    // Erro 500 (Problemas no servidor)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex){
        return buildErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro interno no servidor.");
    }

    // Erro 404 (Recurso não encontrado)
    private ResponseEntity<Object> buildErrorResponse (Exception e, HttpStatus status, String userMessage){
        log.error("Erro [{}]: {}", status, e.getMessage(), e);
        String [] messages = {userMessage};
        ErrorMessage errorMessage = new ErrorMessage(LocalDateTime.now(zoneBrasil), messages, status);
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), status);
    }
}
