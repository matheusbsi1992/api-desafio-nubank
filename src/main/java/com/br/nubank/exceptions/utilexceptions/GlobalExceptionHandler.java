package com.br.nubank.exceptions.utilexceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


/**
 * CLASSE PARA O LANCAMENTO DE EXCECOES*/
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleInvalidFormat(HttpMessageNotReadableException ex) {
        Map<String, String> error = new HashMap<>();

        if (ex.getCause() instanceof InvalidFormatException cause &&
                cause.getTargetType().equals(LocalDate.class)) {
            error.put("dataNascimentoCliente", "DATA DE NASCIMENTO INVALIDA");
        } else {
            error.put("erro", "JSON inválido ou formato incorreto.");
        }

        return ResponseEntity.badRequest().body(error);
    }
}
