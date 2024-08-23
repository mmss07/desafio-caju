package com.caju.autorizador.util;

import com.caju.autorizador.exception.JsonConversionException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UtilJson {
    private UtilJson(){

    }
    public static String convertObjectToJson(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new JsonConversionException("Error converte object para JSON", e);
        }
    }

}
