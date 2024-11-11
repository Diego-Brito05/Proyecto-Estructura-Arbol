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
class HashTable {
    private String[] claves; // Arreglo para almacenar las claves de cada índice
    private ListaPersona[] tabla; // Arreglo para almacenar las listas de personas
    private int size;

    public HashTable(int capacidad) {
        this.tabla = new ListaPersona[capacidad];
        this.claves = new String[capacidad];
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
            claves[indice] = clave; // Guardamos la clave en el arreglo de claves
        } else if (!clave.equals(claves[indice])) {
            // Si hay colisión (diferente clave en el mismo índice), puedes decidir cómo manejarla
            System.out.println("Colisión detectada para la clave: " + clave);
            return;
        }

        // Agregamos la persona a la lista en el índice
        tabla[indice].agregar(persona);
        size++;
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
    public boolean tieneUnSoloElemento(String clave) {
    int indice = hash(clave);

    // Verifica si el índice contiene una lista y si la clave coincide
    if (tabla[indice] != null && clave.equals(claves[indice])) {
        return tabla[indice].longitud() == 1;
    }
    return false; // Retorna falso si no hay elementos o si hay más de uno
    }
}
