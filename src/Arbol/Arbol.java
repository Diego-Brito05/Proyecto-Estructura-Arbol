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
    
        /*Esta funcion se encarga de leer cada elemento de una lista de personas, para luego agregarlos si encuentra a su atributo padre dentro del arbol, siempre toma como raiz al primer 
        elemento de la lista, si el padre no se encuentra en el arbol, el elemento no se agrega al mismo, al final retorna la raiz del arbol.*/
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
            // Crear el nuevo nodo y agregarlo como hijo
            Arbol nuevoNodo = new Arbol(personaActual);
            nodoPadre.agregarHijo(nodoPadre , nuevoNodo);
            
             // Verificar y actualizar el atributo Hijos del padre
            actualizarHijos(nodoPadre.getValor(), personaActual);
        
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
    // Función auxiliar para actualizar el atributo Hijos del padre, si no tiene a uno de sus hijos del arbol en su atributo hijos, se añade al mismo
    private static void actualizarHijos(Persona padre, Persona hijo) {
        String nombreHijoSinApellido = hijo.getNombre().split(" ")[0]; // Extraer el nombre sin apellido

        // Verificar si el nombre del hijo ya está en el atributo Hijos
        if (padre.getHijos() == null || !contieneHijo(padre.getHijos(), nombreHijoSinApellido)) {
            // Agregar el nombre del hijo al atributo Hijos
            String[] nuevosHijos;
            if (padre.getHijos() == null) {
                nuevosHijos = new String[]{nombreHijoSinApellido};
            } else {
                nuevosHijos = new String[padre.getHijos().length + 1];
                System.arraycopy(padre.getHijos(), 0, nuevosHijos, 0, padre.getHijos().length);
                nuevosHijos[nuevosHijos.length - 1] = nombreHijoSinApellido;
            }
            padre.setHijos(nuevosHijos);
        }
    }

    // Función auxiliar para verificar si un nombre está en el arreglo Hijos
    private static boolean contieneHijo(String[] hijos, String nombreHijo) {
        for (String hijo : hijos) {
            if (hijo.equals(nombreHijo) ) {
                return true; // El nombre del hijo ya está presente
            }
        }
        return false; // El nombre del hijo no está presente
    }
    
    // Esta funcion se encarga de al recibir un arbol revisa el atributo hijo de todos los nods y se asegura de que cada hijo del atributo
    // se encuentre en los hijos directos del arbol.
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
                    generarNumeralUnico(), // Generar un numeral único,
                    persona.getNombre(), // El padre es el nodo actual
                    "unknown",
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
        // Variable estática para garantizar la unicidad de los numerales
    private static int contadorNumerales = 1;

    // Método para generar un numeral único
    private static String generarNumeralUnico() {
        return " " + (contadorNumerales++);
    }
}

   