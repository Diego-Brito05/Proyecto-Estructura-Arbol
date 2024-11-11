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
public class NodoPersona {
    public Persona persona;
    public NodoPersona siguiente;

    public NodoPersona(Persona persona) {
        this.persona = persona;
        this.siguiente = null;
    }

    /**
     * @return the persona
     */
    public Persona getPersona() {
        return persona;
    }

    /**
     * @param persona the persona to set
     */
    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    /**
     * @return the siguiente
     */
    public NodoPersona getSiguiente() {
        return siguiente;
    }

    /**
     * @param siguiente the siguiente to set
     */
    public void setSiguiente(NodoPersona siguiente) {
        this.siguiente = siguiente;
    }
    
}