package api;

import TDA.Camino;
import TDA.NodoGrafo;

import java.util.ArrayList;

public interface GrafoTDA {
    void inicializarGrafo();
    void agregarVertice(String cliente, String nombre, int horarioInicio, int horarioFinal);
    void agregarCamino(float distancia, float tiempo, String lugar1, String lugar2 );
    NodoGrafo obtenerVertice(String cliente);
    ArrayList<Camino> adyacentes(String cliente);
    ArrayList<NodoGrafo> vertices();
    NodoGrafo obtenerOrigen();


    //void imprimirGrafo(String lugar);
    //void eliminarVertice(String lugar);
    //void eliminarArista(String lugar, String destino);
    //ConjuntoTDA vertices();
    //boolean existeArista(String lugar, String destino);
}
