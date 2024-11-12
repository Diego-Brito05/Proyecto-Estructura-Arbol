/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LectorJson;
import javax.swing.JOptionPane;
import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import Arbol.Persona;
import EDD.ListaPersona;
/**
 *
 * @author Diego
 */
public class LectorJson {


public File LecturaJson(ListaPersona listaPersonas) {
        // Crear JFileChooser
        JFileChooser jfc = new JFileChooser();
        jfc.setFileFilter(new FileNameExtensionFilter("Archivos JSON", "json"));

        // Abrir diálogo de selección de archivos
        int seleccion = jfc.showOpenDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            // Obtener el archivo seleccionado
            File archivo = jfc.getSelectedFile();

            // Verificar si es un archivo JSON
            if (archivo.getName().toLowerCase().endsWith(".json")) {
                try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                    String linea;
                    String nombre = null, numeral = null, padre = null, mote = null, ojos = null, pelo = null;

                    while ((linea = br.readLine()) != null) {
                        linea = linea.trim();

                        // Verificar y extraer cada atributo necesario
                        if (linea.contains("\"Of his name\"")) {
                            numeral = extraerValor(linea);
                        } else if (linea.contains("\"Born to\"")) {
                            padre = extraerValor(linea);
                        } else if (linea.contains("\"Known throughout as\"")) {
                            mote = extraerValor(linea);
                        } else if (linea.contains("\"Of eyes\"")) {
                            ojos = extraerValor(linea);
                        } else if (linea.contains("\"Of hair\"")) {
                            pelo = extraerValor(linea);
                        } else if (!linea.startsWith("{") && linea.endsWith(": [")) {
                            nombre = linea.substring(1, linea.length() - 3); // Eliminar comillas y dos puntos al final
                        }

                        // Si todos los atributos han sido leídos, crear y almacenar el objeto Persona
                        if (nombre != null && numeral != null && padre != null && mote != null && ojos != null && pelo != null) {
                            Persona persona = new Persona(nombre, numeral, padre, mote, ojos, pelo);
                            listaPersonas.agregar(persona); // Agregar el objeto Persona a la ListaPersona

                            // Restablecer los valores para la siguiente persona
                            nombre = numeral = padre = mote = ojos = pelo = null;
                        }
                    }

                    JOptionPane.showMessageDialog(null, "Archivo JSON leído correctamente.");
                    return archivo;

                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error al leer el archivo: " + e.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "El archivo seleccionado no es un JSON válido.");
            }
        }
        return null;
    }

    // Método auxiliar para extraer el valor de una línea JSON
    private String extraerValor(String linea) {
        int inicio = linea.indexOf(":") + 2;
        int fin = linea.lastIndexOf("\"");
        return linea.substring(inicio, fin);
    }
}