package com.company;

import TDA.Camino;
import TDA.Grafo;
import TDA.NodoGrafo;
import TDA.NodoVivo;
import api.GrafoTDA;

import java.util.ArrayList;

public class AlgoritmoDistribucion {
    GrafoTDA grafo;
    AlgoritmoDistribucion(GrafoTDA grafo) {
        this.grafo=grafo;
    }

    public float calcularCotaInferior (NodoVivo nodoVivo) {

        NodoGrafo nodoInicio = nodoVivo.visitados.get(0);
        NodoGrafo nodoUltimo = nodoVivo.visitados.get(nodoVivo.visitados.size()-1);

        ArrayList<NodoGrafo> nodosNoVisitados = new ArrayList<NodoGrafo>();

        for(NodoGrafo nodoGrafo : grafo.vertices()) {
            if (!nodoVivo.visitados.contains(nodoGrafo)){
                nodosNoVisitados.add(nodoGrafo);
            }
        }


        return nodoVivo.kmParcial + prim(nodosNoVisitados) + menorCamino(nodoInicio, nodosNoVisitados).distanciaTotal + menorCamino(nodoUltimo, nodosNoVisitados).distanciaTotal;
    }

    public Camino menorCamino(NodoGrafo nodoGrafo, ArrayList<NodoGrafo> nodosNoVisitados) {
       Camino menor = new Camino();
       menor.distanciaTotal = Float.MAX_VALUE;
        for (Camino camino : nodoGrafo.caminos) {
            if (nodosNoVisitados.contains(camino.destino)) {
                if (camino.distanciaTotal< menor.distanciaTotal) {
                    menor = camino;
                }
            }
        }
        return menor;
    }




    public Camino menorCamino(ArrayList<Camino> caminos){
        Camino aux = new Camino();
        aux.distanciaTotal=Float.MAX_VALUE;
        for(Camino camino : caminos){
            if(camino.distanciaTotal < aux.distanciaTotal){
                aux = camino;
            }

        }
        return aux;

    }

    public NodoVivo crearRaiz(){
        NodoVivo nodo = new NodoVivo();
        nodo.kmParcial = 0;
        nodo.solucionParcial= new ArrayList<Camino>();
        nodo.visitados = new ArrayList<NodoGrafo>();
        nodo.visitados.add(grafo.obtenerOrigen());
        nodo.etapa=0;
        nodo.tiempoParcial=0;
        nodo.cotaInferior=calcularCotaInferior(nodo);

        return nodo;
    }

    //TODO
    /*
    public boolean podar(NodoVivo nodo, float cota) {
        if(nodo.cotaInferior>cota) {
            return true;
        } else {
            return false;
        }
    }

    public float actualizarCota(NodoVivo nodo, float cota) {
        if(nodo.cotaInferior<cota) {
            return nodo.cotaInferior;
        }
    }
    */

    public float prim(ArrayList<NodoGrafo> nodosNoVisitados) {
        float longitud = 0;
        Camino auxCamino;
        ArrayList<NodoGrafo> auxNoVisitados =new ArrayList<NodoGrafo>(nodosNoVisitados);
        ArrayList<Camino> caminosDisponibles = new ArrayList<Camino>();
        NodoGrafo auxNodo = auxNoVisitados.get(0);
        auxNoVisitados.remove(auxNodo);
        agregarCaminos(auxNodo, caminosDisponibles);
        while(auxNoVisitados.size()!=0){
            auxCamino=menorCamino(caminosDisponibles);
            if(auxNoVisitados.contains(auxCamino.destino)){
                longitud+=auxCamino.distanciaTotal;
                auxNoVisitados.remove(auxCamino.destino);
                agregarCaminos(auxCamino.destino, caminosDisponibles);
            }
            caminosDisponibles.remove(auxCamino);

        }
        return longitud;
    }

    public void agregarCaminos(NodoGrafo nodoGrafo, ArrayList<Camino> caminosDisponibles) {
        for(Camino camino : nodoGrafo.caminos) {
            //CUIDADO CON ORIGEN Y DESTINO DE CAMINO QUE CUENTE LOS INVERSOS
            caminosDisponibles.add(camino);
        }

    }
    public boolean esSolucion(NodoVivo nodo) {
        if (nodo.etapa == grafo.vertices().size()-1) {
            return true;
        } else {
            return false;
        }

    }





    public ArrayList<Camino> greedy () {
        ArrayList<NodoGrafo> sinVisitar = grafo.vertices();
        ArrayList<Camino> recorrido  = new ArrayList<Camino>();
        NodoGrafo aux = sinVisitar.get(0);
        Camino mejorCamino;
        while(sinVisitar.size()>1) {
            mejorCamino = menorCamino(aux, sinVisitar);
            recorrido.add(mejorCamino);
            sinVisitar.remove(aux);
            aux = mejorCamino.destino;
        }
        for(Camino auxCamino: aux.caminos) {
            if (auxCamino.destino==grafo.obtenerOrigen()) {
                recorrido.add(auxCamino);
            }
        }

        return recorrido;

    }


}
