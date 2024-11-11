/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Arbol;

/**
 *
 * @author Diego
 */
public class Arbol {
    private Persona valor;               
    private Arbol primerHijo;            
    private Arbol hermanoDerecho;        

    // Constructor para crear un nodo con un valor de tipo Persona
    public Arbol(Persona valor) {
        this.valor = valor;
        this.primerHijo = null;
        this.hermanoDerecho = null;
    }

    // Método para agregar un hijo al nodo actual
    public void agregarHijo(Arbol hijo) {
        if (primerHijo == null) {
            primerHijo = hijo;
        } else {
            Arbol actual = primerHijo;
            while (actual.hermanoDerecho != null) {
                actual = actual.hermanoDerecho;
            }
            actual.hermanoDerecho = hijo;
        }
    }

    // Método para obtener el valor del nodo
    public Persona getValor() {
        return valor;
    }

    // Método para obtener el primer hijo del nodo
    public Arbol getPrimerHijo() {
        return primerHijo;
    }

    // Método para obtener el siguiente hermano del nodo
    public Arbol getHermanoDerecho() {
        return hermanoDerecho;
    }

    // Método para verificar si el nodo es una hoja (sin hijos)
    public boolean esHoja() {
        return primerHijo == null;
    }

    // Método para recorrer el árbol en preorden y mostrar cada nodo
    public void recorrerPreorden() {
        System.out.print(valor + " ");
        if (primerHijo != null) {
            primerHijo.recorrerPreorden();
        }
        if (hermanoDerecho != null) {
            hermanoDerecho.recorrerPreorden();
        }
    }

    // Método para recorrer el árbol en postorden y mostrar cada nodo
    public void recorrerPostorden() {
        if (primerHijo != null) {
            primerHijo.recorrerPostorden();
        }
        System.out.print(valor + " ");
        if (hermanoDerecho != null) {
            hermanoDerecho.recorrerPostorden();
        }
    }
}