package com.aluracursos.desafio.model2;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosGenero(
       @JsonAlias("name") String genero
) {
}
