package com.br.nubank.exceptions.utilexceptions;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

public class UtilMyBindingResult {

    public static Map<String, String> resultError(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getAllErrors().forEach(error -> {
            String defaultMessage = error.getDefaultMessage();
            String fieldName = ((FieldError) error).getField();
            if (fieldName.contains(".")) {
                fieldName = fieldName.substring(fieldName.lastIndexOf(".") + 1);
            }
            errors.put(fieldName, defaultMessage);
        });
        return errors;
    }
}
