package com.aluracursos.desafio.principal;

import com.aluracursos.desafio.model.Datos;
import com.aluracursos.desafio.model.DatosLibros;
import com.aluracursos.desafio.service.ConsumoAPI;
import com.aluracursos.desafio.service.ConvierteDatos;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    /* SCANNER */
    private Scanner teclado = new Scanner(System.in);

    /* INSTANCIAS DE OTRAS CLASES*/
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();

    /* DATOS DE LA URL */
    private static final String URL_BASE = "https://gutendex.com/books/";



    public void muestraElMenu(){

        /* Convertimos los datos json a Codigo Java */
        var json = consumoAPI.obtenerDatos(URL_BASE);
        System.out.println(json);

        /* Convertimos el Codigo Java a las respectivas instancias de nuestras clases*/
        var datos = conversor.obtenerDatos(json, Datos.class);
        System.out.println(datos);

        /* TOP 10 LIBROS MAS DESCARGADOS */
        System.out.println("Top 10 libros mas descargados");
        datos.resultados().stream()
                .sorted(Comparator.comparing(DatosLibros::numeroDeDescargas).reversed())
                .limit(10)
                .map(l -> l.titulo().toUpperCase())
                .forEach(System.out::println);

        /* BUSQUEDA DE LIBRO POR NOMBRE */
        System.out.println("Ingrese el nombre del libro que desea buscar");
        var tituloLibro = teclado.nextLine();
        json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);

            // Usar la clase Optional por si tiene o no resultados la busqueda
        Optional<DatosLibros> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();

            // Usaremos metodos de la clase Optional para ver si esta presente o no el resultado
        if(libroBuscado.isPresent()){
            System.out.println("Libro encontrado ");
            System.out.println(libroBuscado.get());
        } else {
            System.out.println("Libro no encontrado");
        }


        /* TRABAJANDO CON ESTADISTICAS */
        DoubleSummaryStatistics est = datos.resultados().stream()
                .filter(d -> d.numeroDeDescargas() > 0)
                .collect(Collectors.summarizingDouble(DatosLibros::numeroDeDescargas));
        System.out.println("Cantidad media de descargas: " + est.getAverage());
        System.out.println("Cantidad maxima de descargas " + est.getMax());
        System.out.println("Cantidad minima de descargas " + est.getMin());
        System.out.println("Cantidad de registros evaluados para calcular las estadisticas " + est.getCount());
    }


}
