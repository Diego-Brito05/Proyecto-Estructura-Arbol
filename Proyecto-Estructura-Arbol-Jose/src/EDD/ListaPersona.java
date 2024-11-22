/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;
import Arbol.Persona;
/**
 *
 * @author Diego
 */
public class ListaPersona {
    private NodoPersona cabeza;

    public ListaPersona() {
        this.cabeza = null;
    }
    

    // Método para agregar una persona al final de la lista
    public void agregar(Persona persona) {
        NodoPersona nuevoNodo = new NodoPersona(persona);
        if (getCabeza() == null) {
            cabeza = nuevoNodo;
        } else {
            NodoPersona actual = getCabeza();
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
    }
    
    // Método para obtener un arreglo con todas las personas en la lista
    public Persona[] obtenerPersonas() {
        int longitud = longitud(); // Calcula el tamaño de la lista
        Persona[] personas = new Persona[longitud];
        NodoPersona actual = getCabeza();
        int index = 0;

        while (actual != null) {
            personas[index++] = actual.persona; // Agrega cada persona al arreglo
            actual = actual.siguiente;
        }
        return personas; // Devuelve el arreglo con todas las personas
    }
    
    // Método para obtener la longitud de la lista
    public int longitud() {
        int longitud = 0;
        NodoPersona actual = getCabeza();
        while (actual != null) {
            longitud++;
            actual = actual.siguiente;
        }
        return longitud;
    }

    // Método para eliminar todos los nodos de la lista
    public void eliminarTodos() {
        cabeza = null;
    }

    /**
     * @return the cabeza
     */
    public NodoPersona getCabeza() {
        return cabeza;
    }
}