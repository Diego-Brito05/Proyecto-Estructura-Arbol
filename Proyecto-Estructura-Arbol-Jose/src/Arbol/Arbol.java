/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Arbol;
import EDD.NodoPersona;
import EDD.ListaPersona;
/**
 *
 * @author Diego
 */
public class Arbol {
    private Persona valor;               
    private Arbol primerHijo;            
    private Arbol hermanoDerecho;
      
    
    // Constructor para crear un nodo con un valor de tipo Persona
    public Arbol(Persona persona) {
        this.valor = persona;
        this.primerHijo = null;
        this.hermanoDerecho = null;
        
    }

    // Método para agregar un hijo al nodo actual
    public void agregarHijo(Arbol padre,Arbol hijo) {
        if (padre.primerHijo == null) {
            padre.primerHijo = hijo;
        } else {
            Arbol actual = padre.primerHijo;
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
        public static Arbol crearArbolDesdeLista(ListaPersona listaPersonas) {
        

        // Tomar la primera persona como raíz
        NodoPersona actual = listaPersonas.getCabeza();
        Persona raizPersona = actual.persona;
        Arbol raiz = new Arbol(raizPersona);

        // Recorrer la lista para construir el árbol
        actual = actual.siguiente; // Avanzamos al siguiente nodo
        while (actual != null) {
            Persona personaActual = actual.persona;
            String padreInfo = personaActual.getPadre();

            if ("[unknown]".equals(padreInfo) || padreInfo.isEmpty()) {
                throw new IllegalStateException("La persona " + personaActual.getNombre() + " no tiene padre especificado.");
            }

            // Buscar el nodo padre en el árbol
            Arbol nodoPadre = buscarNodoPorPadre(raiz, padreInfo);
            if (nodoPadre == null) {
                throw new IllegalStateException("No se encontró el nodo padre: " + padreInfo);
            }
            Arbol nuevoNodo = new Arbol(personaActual);
            nodoPadre.agregarHijo(nodoPadre , nuevoNodo);

            actual = actual.siguiente; // Avanzamos al siguiente nodo
        }      
        return raiz; // Devolvemos la raíz del árbol
    }
         // Método para buscar un nodo padre por el nombre o mote
    private static Arbol buscarNodoPorPadre(Arbol nodoActual, String padreInfo) {
    if (nodoActual == null) {
        return null;
    }

    // Generar "nombre, numeral" para el nodo actual
    Persona persona = nodoActual.getValor();
    String identificadorNodo = persona.getNombre() + ", " + persona.getNumeral()+" of his name";

    // Verificar si padreInfo coincide con el identificador o con el mote
    if (identificadorNodo.equalsIgnoreCase(padreInfo) || 
        (persona.getMote() != null && persona.getMote().equalsIgnoreCase(padreInfo))|| persona.getNombre().equalsIgnoreCase(padreInfo)) {
        return nodoActual;
    }

    // Buscar recursivamente en los hijos
    Arbol hijo = nodoActual.getPrimerHijo();
    while (hijo != null) {
        Arbol resultado = buscarNodoPorPadre(hijo, padreInfo);
        if (resultado != null) {
            return resultado; // Retornar el nodo encontrado
        }
        hijo = hijo.getHermanoDerecho(); // Pasar al siguiente hermano
    }

    // Si no se encuentra, retornar null
    return null;
    }
    

    public void validarYCompletarHijosPreorden(Arbol nodoActual) {
    if (nodoActual == null) {
        return;
    }

    // Obtener la persona del nodo actual
    Persona persona = nodoActual.getValor();
    if (persona.getHijos() != null) {
        for (String nombreHijo : persona.getHijos()) {
            // Verificar si el nombre del hijo está representado como un nodo hijo
            if (!nodoActual.existeHijo(nodoActual ,nombreHijo)) {
                // Si no está, crear una nueva Persona con valores desconocidos
                Persona hijoNoEnc = new Persona(
                    nombreHijo,
                    " ",
                    persona.getNombre(), // El padre es el nodo actual
                    "unknown",
                    "unknown",
                    "unknown",
                    null
                );

                // Agregar el nuevo nodo al árbol
                nodoActual.agregarHijo(nodoActual,new Arbol(hijoNoEnc));
            }
        }
    }

    // Recorrer recursivamente los hijos en preorden
    Arbol hijo = nodoActual.getPrimerHijo();
    while (hijo != null) {
        validarYCompletarHijosPreorden(hijo);
        hijo = hijo.getHermanoDerecho();
    }
}

    // Método para verificar si un hijo con un nombre dado ya existe en los hijos de este nodo
    private boolean existeHijo(Arbol padre,String nombreHijo) {
        Arbol actual = padre.primerHijo;
        while (actual != null) {
            // Extraer solo el nombre (primera parte antes del espacio) del nombre completo
            String nombreCompleto = actual.getValor().getNombre();
            String nombreActual = nombreCompleto.split(" ")[0]; // Tomar la primera palabra del nombre completo

            // Comparar el nombre extraído con el nombre en el atributo "hijos"
            if (nombreHijo.equals(nombreActual)) {
                return true;
            }
            actual = actual.hermanoDerecho;
        }
        return false;
    }
}

   