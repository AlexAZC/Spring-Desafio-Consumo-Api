package com.aluracursos.desafio.principal2;


import com.aluracursos.desafio.model2.Datos;
import com.aluracursos.desafio.model2.DatosJuegos;
import com.aluracursos.desafio.service2.ConsumoAPI;
import com.aluracursos.desafio.service2.ConvierteDatos;

import java.util.Comparator;
import java.util.Optional;
import java.util.Scanner;

public class Principal2 {

    /* SCANNER */
    Scanner teclado = new Scanner(System.in);

    /* INSTANCIAS DE OTRAS CLASES */
    private ConsumoAPI consumoapi = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();

    /* URL PARA LA CONVERSION */
    private final static String Url = "https://api.rawg.io/api/games?";
    private final static String Key = "key=5e6e52cd36a14b709b15c46a7559bea3";



    public void menuPrincipal() {

        var json = consumoapi.ConsumirApi(Url + Key);

        var datos = conversor.convertirDatos(json, Datos.class);

        System.out.println("TOP 10 MEJORES JUEGOS DE STEAM SEGUN USUARIOS");
        datos.datosJuegos().stream()
                .sorted(Comparator.comparing(DatosJuegos::puntaje).reversed())
                .limit(10)
                .map(j -> j.nombre())
                .forEach(System.out::println);

        System.out.println("Por favor escriba el juego del que desee buscar informacion");
        String busqueda = teclado.nextLine();

        json = consumoapi.ConsumirApi(Url + Key + "&search=" + busqueda.replace(" ", "-"));

        var datosDeBusqueda = conversor.convertirDatos(json, Datos.class);

        Optional<DatosJuegos> datosBuscados = datosDeBusqueda.datosJuegos().stream()
                .filter(j -> j.nombre().toUpperCase().contains(busqueda.toUpperCase()))
                .findFirst();

        if(datosBuscados.isPresent()){
            System.out.println("Se encontro un resultado");
            System.out.println(datosBuscados.get());
        } else{
            System.out.println("No se encontro ni un resultado");
        }






    }
}
