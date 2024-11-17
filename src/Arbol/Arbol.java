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
        public static Arbol crearArbolDesdeLista(ListaPersona listaPersonas) {
        if (listaPersonas == null || listaPersonas.getCabeza() == null) {
            throw new IllegalArgumentException("La lista de personas está vacía.");
        }

        // Tomar la primera persona como raíz
        NodoPersona actual = listaPersonas.getCabeza();
        Persona raizPersona = actual.persona;
        Arbol raiz = new Arbol(raizPersona);

        // Recorrer la lista para construir el árbol
        actual = actual.siguiente; // Avanzamos al siguiente nodo
        while (actual != null) {
            Persona personaActual = actual.persona;
            String padreInfo = personaActual.getPadre();

            if (padreInfo == null || padreInfo.isEmpty()) {
                throw new IllegalStateException("La persona " + personaActual.getNombre() + " no tiene padre especificado.");
            }

            // Buscar el nodo padre en el árbol
            Arbol nodoPadre = buscarNodoPorPadre(raiz, padreInfo);
            if (nodoPadre == null) {
                throw new IllegalStateException("No se encontró el nodo padre: " + padreInfo);
            }

            // Crear el nuevo nodo y agregarlo como hijo
            Arbol nuevoNodo = new Arbol(personaActual);
            nodoPadre.agregarHijo(nuevoNodo);

            actual = actual.siguiente; // Avanzamos al siguiente nodo
        }
        // Validar y completar los hijos del árbol
        raiz.validarYCompletarHijos();
        return raiz; // Devolvemos la raíz del árbol
    }
         // Método para buscar un nodo padre por el nombre o mote
    private static Arbol buscarNodoPorPadre(Arbol nodo, String padreInfo) {
        if (nodo == null) {
            return null;
        }

        // Caso 1: Buscar por nombre exacto
        if (padreInfo.equals(nodo.getValor().getNombre())) {
            return nodo;
        }

        // Caso 2: Buscar por mote
        if (padreInfo.equals(nodo.getValor().getMote())) {
            return nodo;
        }

        // Caso 3: Si contiene una coma, extraer el nombre (parte antes de la coma)
        if (padreInfo.contains(",")) {
            String nombre = padreInfo.split(",")[0].trim();
            if (nombre.equals(nodo.getValor().getNombre())) {
                return nodo;
            }
        }

        // Recursión: buscar en hijos y hermanos
        Arbol encontrado = buscarNodoPorPadre(nodo.getPrimerHijo(), padreInfo);
        if (encontrado != null) {
            return encontrado;
        }
        return buscarNodoPorPadre(nodo.getHermanoDerecho(), padreInfo);
    }
    public void validarYCompletarHijos() {
    if (valor == null) return;

    // Verificar los hijos declarados en el atributo "hijos" de la Persona
    if (valor.getHijos() != null) {
        for (String nombreHijo : valor.getHijos()) {
            // Si no existe un hijo con ese nombre, agregarlo al árbol
            if (!existeHijo(nombreHijo)) {
                // Extraer el apellido del padre del atributo "nombre"
                String apellidoPadre = extraerApellido(valor.getNombre());

                // Construir el nombre completo del hijo usando el apellido del padre
                String nombreCompletoHijo = nombreHijo + " " + apellidoPadre;

                // Crear un nuevo objeto Persona con datos predeterminados
                Persona hijoNoEnc = new Persona(nombreCompletoHijo, "unknown", valor.getNombre(), "unknown", "unknown", "unknown", null);

                // Agregar el nuevo hijo al árbol
                agregarHijo(new Arbol(hijoNoEnc));
            }
        }
    }

    // Recursión para los hijos del nodo actual
    if (primerHijo != null) {
        primerHijo.validarYCompletarHijos();
    }
    if (hermanoDerecho != null) {
        hermanoDerecho.validarYCompletarHijos();
    }
    }

    // Método para verificar si un hijo con un nombre dado ya existe en los hijos de este nodo
    private boolean existeHijo(String nombreHijo) {
        Arbol actual = primerHijo;
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

    // Método auxiliar para extraer el apellido del nombre completo
    private String extraerApellido(String nombreCompleto) {
        String[] partes = nombreCompleto.split(" ");
        if (partes.length > 1) {
            return partes[1]; // Devolver la segunda parte como apellido
        }
        return "unknown"; // En caso de que no haya apellido, devolver "unknown"
    }
}

   