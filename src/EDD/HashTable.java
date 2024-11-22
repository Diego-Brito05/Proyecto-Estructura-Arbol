/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;
import Arbol.Persona;
import Arbol.Arbol;
/**
 *
 * @author Diego
 */
public class HashTable {
    private String[] claves; // Arreglo para almacenar las claves de cada índice
    private ListaPersona[] tabla; // Arreglo para almacenar las listas de personas
    private int size;

    public HashTable() {
        this.tabla = new ListaPersona[200];
        this.claves = new String[200];
        this.size = 0;
    }

    // Método hash para generar un índice a partir de la clave
    private int hash(String clave) {
        return Math.abs(clave.hashCode()) % tabla.length;
    }

    // Método para agregar una persona con una clave específica
    public void put(String clave, Persona persona) {
        int indice = hash(clave);

        // Si no hay lista en el índice, creamos una nueva y guardamos la clave
        if (tabla[indice] == null) {
            tabla[indice] = new ListaPersona();
            claves[indice] = clave;  // Guardamos la clave en el arreglo de claves
        }

        // Si las claves coinciden, agregamos la persona al final de la lista
        if (clave.equals(claves[indice])) {
            tabla[indice].agregar(persona);  // Agrega al final de la lista
        } else {
            // Si hay colisión con una clave diferente, mostramos un mensaje y no hacemos nada
            System.out.println("Colisión detectada para la clave: " + clave);
            return;
        }

        size++;
    }

    // Método para agregar las personas de un árbol al HashTable
    public void putArbol(Arbol arbol) {
        if (arbol == null) return; // Caso base: si el árbol está vacío, no hacemos nada

        // Agregar el valor actual (Persona) al HashTable
        Persona persona = arbol.getValor(); // Obtener la persona en el nodo actual
        String clave = obtenerClaveDePersona(persona); // Generar clave a partir de la persona
        put(clave, persona); // Agregar al HashTable

        // Recorrer el primer hijo
        putArbol(arbol.getPrimerHijo());

        // Recorrer el hermano derecho
        putArbol(arbol.getHermanoDerecho());
    }

    // Método para obtener la clave de una persona (por ejemplo, el primer nombre)
    private String obtenerClaveDePersona(Persona persona) {
        // Suponiendo que la clave es el primer nombre (antes de la coma)
        String[] partes = persona.getNombre().split(" ");
        return partes[0];  // Primer nombre
    }

    // Método para obtener la lista de personas asociadas a una clave
    public Persona[] get(String clave) {
        int indice = hash(clave);
        if (tabla[indice] != null && clave.equals(claves[indice])) {
            return tabla[indice].obtenerPersonas();
        }
        return new Persona[0];  // Retorna un array vacío si no hay personas para esa clave
    }

    // Método para eliminar todas las personas asociadas a una clave
    public void remove(String clave) {
        int indice = hash(clave);
        if (tabla[indice] != null && clave.equals(claves[indice])) {
            tabla[indice].eliminarTodos();
            tabla[indice] = null;
            claves[indice] = null;
            size--;
        }
    }

    public int size() {
        return size;
    }

    public boolean containsKey(String clave) {
        int indice = hash(clave);
        return tabla[indice] != null && clave.equals(claves[indice]);
    }

    // Método que detecta si hay solo un elemento en el índice
    public boolean tieneUnSoloElemento(String clave) {
        int indice = hash(clave);

        // Verifica si el índice contiene una lista y si la clave coincide
        if (tabla[indice] != null && clave.equals(claves[indice])) {
            return tabla[indice].longitud() == 1;
        }
        return false; // Retorna falso si no hay elementos o si hay más de uno
    }
}
