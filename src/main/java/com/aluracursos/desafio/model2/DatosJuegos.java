package com.aluracursos.desafio.model2;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosJuegos(
        @JsonAlias("name") String nombre,
        @JsonAlias("released") String fechaDeLanzamiento,
        @JsonAlias("rating") Double puntaje,
        @JsonAlias("ratings") List<DatosPuntaje> datosPuntajes,
        @JsonAlias("genres") List<DatosGenero> datosGenero
) {
}
