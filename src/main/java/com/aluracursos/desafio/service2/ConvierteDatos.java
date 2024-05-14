package com.aluracursos.desafio.service2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos implements IConvierteDatos{
private ObjectMapper mapeador = new ObjectMapper();

    @Override
    public <T> T convertirDatos(String json, Class<T> clase) {
        try {
            return mapeador.readValue(json, clase);
        } catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }

    }
}
