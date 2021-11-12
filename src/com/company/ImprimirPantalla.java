package com.company;

import TDA.Camino;

import java.util.ArrayList;

public class ImprimirPantalla {
    public static void mostrarCamino(ArrayList<Camino> recorrido) {
        float total = 0;
        for(Camino camino:recorrido){
            System.out.print(camino.origen.cliente + "-"+ camino.distanciaTotal +"->");
            total=total + camino.distanciaTotal;
        }
        System.out.println();
        System.out.println(total);
    }
}
