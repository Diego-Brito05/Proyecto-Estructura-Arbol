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
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            NodoPersona actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
    }
    
    // Método para obtener la longitud de la lista
    public int longitud() {
        int longitud = 0;
        NodoPersona actual = cabeza;
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
}