package com.aluracursos.desafio.model2;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosPuntaje(
       @JsonAlias("title") String opinion,
       @JsonAlias("count") Integer cuentas,
       @JsonAlias("percent") Double porcentaje
) {
}
