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
        return Math.abs(clave.hashCode()) % getTabla().length;
    }

    // Método para agregar una persona con una clave específica
    public void put(String clave, Persona persona) {
        int indice = hash(clave);

        // Buscar un índice disponible usando sondeo lineal
        while (getTabla()[indice] != null && !clave.equals(claves[indice])) {
            // Si la clave es diferente, avanzamos al siguiente índice
            indice = (indice + 1) % getTabla().length; // Cicla al inicio si llega al final
        }

        // Si encontramos un índice vacío, inicializamos la lista y almacenamos la clave
        if (getTabla()[indice] == null) {
            tabla[indice] = new ListaPersona();
            claves[indice] = clave;
        }

        // Agregamos la persona a la lista en el índice calculado
        getTabla()[indice].agregar(persona);
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

    // Método para obtener la clave de una persona (el primer nombre)
    private String obtenerClaveDePersona(Persona persona) {
        // Suponiendo que la clave es el primer nombre 
        String[] partes = persona.getNombre().split(" ");
        return partes[0];  // Primer nombre
    }

    // Método para obtener la lista de personas asociadas a una clave
    public Persona[] get(String clave) {
        int indice = hash(clave);
        if (getTabla()[indice] != null && clave.equals(getClaves()[indice])) {
            return getTabla()[indice].obtenerPersonas();
        }
        return new Persona[0];  // Retorna un array vacío si no hay personas para esa clave
    }

    // Método para eliminar todas las personas asociadas a una clave
    public void remove(String clave) {
        int indice = hash(clave);
        if (getTabla()[indice] != null && clave.equals(getClaves()[indice])) {
            getTabla()[indice].eliminarTodos();
            tabla[indice] = null;
            claves[indice] = null;
            size--;
        }
    }

    public int size() {
        return getSize();
    }
    //verifica si la clave que se esta pasando como parametro se encuntra en el HashTable y retona un booleano
    public boolean containsKey(String clave) {
        int indice = hash(clave);
        return getTabla()[indice] != null && clave.equals(getClaves()[indice]);
    }

    // Método que detecta si hay solo un elemento en el índice
    public boolean tieneUnSoloElemento(String clave) {
        int indice = hash(clave);

        // Verifica si el índice contiene una lista y si la clave coincide
        if (getTabla()[indice] != null && clave.equals(getClaves()[indice])) {
            return getTabla()[indice].longitud() == 1;
        }
        return false; // Retorna falso si no hay elementos o si hay más de uno
    }
    // Método para encontrar el índice asociado a una clave
    public int buscarIndice(String clave) {
    int indice = hash(clave); // Calcular el índice inicial usando la función hash

    // Buscar el índice con la clave correcta usando sondeo lineal
    while (getTabla()[indice] != null) {
        if (clave.equals(getClaves()[indice])) {
            return indice; // Encontramos la clave, retornamos el índice
        }
        indice = (indice + 1) % getTabla().length; // Avanzar al siguiente índice
    }

    // Si no encontramos la clave, retornamos -1
    return -1;
    }

    /**
     * @return the claves
     */
    public String[] getClaves() {
        return claves;
    }

    /**
     * @return the tabla
     */
    public ListaPersona[] getTabla() {
        return tabla;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }
    
    public Persona[] getPersonasPorNombre(String nombre) {
    // Primero, calculamos cuántas personas coinciden con el nombre
    int contador = 0;

    // Recorremos toda la tabla de listas de personas
    for (ListaPersona lista : getTabla()) {
        if (lista != null) {
            // Si la lista no está vacía, buscamos las personas que coincidan con el nombre
            for (Persona persona : lista.obtenerPersonas()) {
                if (persona.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                    contador++;
                }
            }
        }
    }

    // Si no se encontraron personas, devolvemos un arreglo vacío
    if (contador == 0) {
        return new Persona[0];
    }

    // Creamos un arreglo con el tamaño del contador
    Persona[] resultado = new Persona[contador];
    int index = 0;

    // Recorremos nuevamente la tabla y agregamos las personas encontradas al arreglo
    for (ListaPersona lista : getTabla()) {
        if (lista != null) {
            for (Persona persona : lista.obtenerPersonas()) {
                if (persona.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                    resultado[index++] = persona;
                }
            }
        }
    }

    return resultado;
}
    
}
