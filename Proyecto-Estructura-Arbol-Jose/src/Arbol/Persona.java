/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Arbol;

/**
 *
 * @author Diego
 */
public class Persona {
    private String nombre;
    private String numeral;
    private String padre;
    private String mote;
    private String ojos;
    private String pelo;
    private String[] hijos;
    private Integer indice;
    
    public Persona(String nombre,String numeral,String padre, String mote,String ojos, String pelo, String[] hijos) {
        this.nombre = nombre;
        this.numeral = numeral;
        this.padre = padre;
        this.mote = mote;
        this.ojos = ojos;
        this.pelo = pelo;
        this.hijos = hijos;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the numeral
     */
    public String getNumeral() {
        return numeral;
    }

    /**
     * @return the padre
     */
    public String getPadre() {
        return padre;
    }

    /**
     * @return the mote
     */
    public String getMote() {
        return mote;
    }

    /**
     * @return the ojos
     */
    public String getOjos() {
        return ojos;
    }

    /**
     * @return the pelo
     */
    public String getPelo() {
        return pelo;
    }
     /**
     * @return the Hijos
     */
    public String[] getHijos() {
        return hijos;
    }
    
}
    

