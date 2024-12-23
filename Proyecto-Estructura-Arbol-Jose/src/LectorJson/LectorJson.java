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

/* Esta funcion se encarga de revisar si es un archivo.json, en el caso que lo sea revisa en cada linea si esta guardado la informacion en el formato adecuado,
    cuando encuentra los atributos necesarios para crear a una persona, crea la persona, la agrega a una lista de personas y devuelve a null todos las variables en las que se guarda la informacion
    de cada persona, al final retorna el archivo .json    
    */
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
                    String nombre = null, numeral = null, padre = null, mote = null,titulo=null, ojos = null, pelo = null;
                    String[] hijos = new String[0];  // Inicializamos como arreglo vacío

                    while ((linea = br.readLine()) != null) {
                        linea = linea.trim();

                        // Verificar y extraer cada atributo necesario
                        if (linea.contains("\"Of his name\"") && numeral==null) {
                            numeral = extraerValor(linea);
                        } else if (linea.contains("\"Born to\"") && padre == null) {
                            padre = extraerValor(linea);
                        } else if (linea.contains("\"Known throughout as\"")) {
                            mote = extraerValor(linea);
                        }else if (linea.contains("\"Held title\"")) {
                           titulo = extraerValor(linea);                       
                        } else if (linea.contains("\"Of eyes\"")) {
                            ojos = extraerValor(linea);
                        } else if (linea.contains("\"Of hair\"")) {
                            pelo = extraerValor(linea);
                        } else if (linea.contains("\"Father to\"")) {
                            hijos = extraerHijos(br , linea);  // Obtener los nombres de los hijos
                        } else if (linea.contains("]")) {
                            // Asegurarse de que hijos no sea null si no se encontró antes
                            if (hijos.length<1 && nombre != null) {
                                hijos = new String[]{"No children"}; // Asignar mensaje indicando que no tiene hijos
                            }
                        } else if (!linea.contains("House") && linea.endsWith(":[") && nombre == null) {
                            // Extraer el nombre eliminando las comillas y el ":[" al final
                            nombre = linea.substring(1, linea.length() - 3).trim();
                        }

                        // Si todos los atributos han sido leídos, crear y almacenar el objeto Persona
                        if (nombre != null && numeral != null && padre != null && ojos != null && pelo != null && hijos.length >= 1) {
                            // Verificar y completar si falta mote
                            if (mote == null) {
                                mote = "unknown";                              
                            }
                            if (titulo == null) {
                                titulo = "unknown";                              
                            }
                            Persona persona = new Persona(nombre, numeral, padre, mote,titulo, ojos, pelo, hijos);
                            listaPersonas.agregar(persona); // Agregar el objeto Persona a la ListaPersona

                            // Restablecer los valores para la siguiente persona
                            nombre = numeral = padre = mote = ojos = pelo = null;
                            hijos = new String[0]; // Resetear a arreglo vacio para la próxima persona
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
    //Metodo auxiliar en el que se validan todas las lineas que cumplan con la condicion de ser atributos hijos, para luego guardarlos en un arreglo de strings 
    //y luego retornarlos a la funcion principal
   private String[] extraerHijos(BufferedReader br, String primeraLinea) throws IOException {
    StringBuilder contenidoHijos = new StringBuilder();

    // Comenzar con la primera línea
    contenidoHijos.append(primeraLinea.substring(primeraLinea.indexOf("[") + 1).trim());

    String linea;
    while ((linea = br.readLine()) != null) {
        // Remover espacios y verificar si es la última línea del arreglo
        linea = linea.trim();
        if (linea.endsWith("]")) {
            // Agregar el contenido final del arreglo y salir del bucle
            contenidoHijos.append(" ").append(linea, 0, linea.indexOf("]"));
            break;
        } else {
            // Continuar acumulando líneas
            
            contenidoHijos.append(" ").append(linea);
        }
    }

    String hijosStr = contenidoHijos.toString().trim();
    if (!hijosStr.isEmpty()) {
        String[] nombresHijos = hijosStr.split(",");
        for (int i = 0; i < nombresHijos.length; i++) {
            // Limpiar cada nombre de comillas y espacios
            nombresHijos[i] = nombresHijos[i].trim().replace("\"", "");

            // Dividir el string en partes y guardar solo lo anterior al espacio
            String[] partes = nombresHijos[i].split(" ");
            nombresHijos[i] = partes[0]; // Guarda la primera palabra (antes del espacio)
        }
        return nombresHijos;
    }

    // Si no se encuentran nombres, devolver un arreglo vacío
    return new String[0];
    }
}