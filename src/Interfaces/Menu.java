/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaces;

import EDD.HashTable;
import Arbol.Arbol;
import Arbol.Persona;
import EDD.ListaPersona;
import LectorJson.LectorJson;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.swing_viewer.ViewPanel;
import org.graphstream.ui.view.Viewer;

/**
 *
 * @author Diego
 */
public class Menu extends javax.swing.JFrame {
    private Arbol arbol;
    private static File archivo;
    private static Graph familia;
    private static String estiloNodo = 
            "size: 20px; " + 
            "shape: diamond; " + 
            "fill-color: blue; " + 
            "text-size: 20; " +  
            "text-alignment: at-right; " + 
            "shadow-mode: plain; " + 
            "shadow-color: black; " + 
            "shadow-width: 5px; " + 
            "shadow-offset: 0px; " + 
            "text-offset: 5px, 5px;";
    /**
     * Creates new form Menuu
     */
    public Menu() {
        this.setVisible(true);
        initComponents();
        this.setLocationRelativeTo(null);
        this.arbol=null;
        this.archivo=null;
        this.familia=null;
        this.setLocationRelativeTo(null);
        }
    
    public Arbol getArbol() {
        return arbol;
    }

    public void setArbol(Arbol arbol) {
        this.arbol = arbol;
    }

    public File getArchivo() {
        return archivo;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

    public Graph getFamilia(){
        return familia;
    }
    


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        MostrarGrafo = new javax.swing.JButton();
        CargaArchivo = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        Mant = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        BuscarAntepasados = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MostrarGrafo.setBackground(new java.awt.Color(153, 51, 255));
        MostrarGrafo.setText("MOSTRAR GRAFO");
        MostrarGrafo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MostrarGrafoActionPerformed(evt);
            }
        });
        jPanel1.add(MostrarGrafo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 390, 280, -1));

        CargaArchivo.setBackground(new java.awt.Color(153, 51, 255));
        CargaArchivo.setText("CARGAR ARCHIVO");
        CargaArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CargaArchivoActionPerformed(evt);
            }
        });
        jPanel1.add(CargaArchivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 350, 280, -1));

        jButton1.setBackground(new java.awt.Color(153, 51, 255));
        jButton1.setText("X");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 480, 90, 20));
        jPanel1.add(Mant, new org.netbeans.lib.awtextra.AbsoluteConstraints(294, 50, 150, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Mostrar Antepasados");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, -1, -1));

        BuscarAntepasados.setText("Buscar");
        BuscarAntepasados.setToolTipText("");
        BuscarAntepasados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarAntepasadosActionPerformed(evt);
            }
        });
        jPanel1.add(BuscarAntepasados, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    

    
    private void CargaArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CargaArchivoActionPerformed
    LectorJson LeerJson =new LectorJson();
        HashTable hashtable= new HashTable(); 
        ListaPersona personas = new ListaPersona();
        File archivo = LeerJson.LecturaJson(personas);
        setArchivo(archivo);
        setArbol(null);
        this.arbol=(Arbol.crearArbolDesdeLista(personas));
        this.arbol.validarYCompletarHijosPreorden(this.arbol);
        hashtable.putArbol(this.arbol);
        setArbol(arbol);
        JOptionPane.showMessageDialog(null, "Se ha cargado el archivo");
                         
    }//GEN-LAST:event_CargaArchivoActionPerformed
    
    /*
    Esta funcion se encarga de crear un Jpanel, para luego añadirlo a un SwingViewer que tiene el grafo para luego mostrarlo
    */
    public void displayGraph(Graph graph2) {
    // Crear la ventana para mostrar el grafo
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
    // Crear un panel con un diseño personalizado
    JPanel panel = new JPanel(new GridLayout()) {
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(1600, 960);  // Ajusta el tamaño de la ventana
        }
    };

    frame.setSize(panel.getWidth(), panel.getHeight());

    // Crear el Viewer para mostrar el grafo
    Viewer viewer = new SwingViewer(graph2, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);

    // Aplicar el layout antes de la visualización
    graph2.setAttribute("ui.layout", "FruchtermanReingold"); // Aplica el layout SpringBox
    viewer.enableAutoLayout();

    
    // Obtener el panel de visualización y agregarlo al panel principal
    ViewPanel viewPanel = (ViewPanel) viewer.addDefaultView(false);
    panel.add(viewPanel);
    frame.add(panel);

    frame.pack();  // Ajustar tamaño del frame según el contenido
    frame.setLocationRelativeTo(null);  // Centrar la ventana en la pantalla
    frame.setVisible(true);
    viewer.enableAutoLayout();
    
    
    // Agregar controlador de eventos para capturar clics sobre los nodos
    viewPanel.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mousePressed(java.awt.event.MouseEvent e) {
            if (e.getButton() == java.awt.event.MouseEvent.BUTTON1) {  // Botón izquierdo del ratón
                // Obtener las coordenadas del clic
                int x = e.getX();
                int y = e.getY();

                // Imprimir las coordenadas del clic
                System.out.println("Clic detectado en: " + x + ", " + y);

                // Iterar sobre todos los nodos del grafo para verificar si el clic es sobre un nodo
                for (Node node : graph2) {
                    // Obtener las coordenadas actuales del nodo
                    Double nodeX = node.getAttribute("x", Double.class);
                    Double nodeY = node.getAttribute("y", Double.class);
                    

                    // Verificar si las coordenadas están disponibles
                    if (nodeX != null && nodeY != null) {
                        // Imprimir las coordenadas del nodo
                        System.out.println("Nodo " + node.getId() + " tiene coordenadas: x = " + nodeX + ", y = " + nodeY);

                        // Verificar si el clic está cerca del nodo
                        double nodeRadius = 20; // Ajustar según el tamaño de los nodos
                        if (Math.abs(x - nodeX) < nodeRadius && Math.abs(y - nodeY) < nodeRadius) {
                            // Si el clic es sobre el nodo
                            System.out.println("Nodo clickeado: " + node.getId());

                            // Recuperar el atributo 'persona' asociado con el nodo
                            Persona persona = (Persona) node.getAttribute("persona");

                            if (persona != null) {
                                String hijos = persona.getHijos() != null ? String.join(", ", persona.getHijos()) : "No tiene hijos";
                                String message = "Información del Nodo:\n" +
                                                 "Nombre: " + persona.getNombre() + "\n" +
                                                 "Numeral: " + persona.getNumeral() + "\n" +
                                                 "Hijos: " + hijos;

                                // Mostrar un JOptionPane con los detalles del nodo
                                JOptionPane.showMessageDialog(null, message, "Detalle del Nodo", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                System.out.println("El nodo no tiene un atributo 'persona'.");
                            }
                        }
                    } else {
                        System.out.println("El nodo " + node.getId() + " no tiene coordenadas.");
                    }
                }
            }
        }
    });
}
    
    /*
    Este metodo se encarga de crear el grafo con la libreria graphstream para luego ser mostrado
    
    */
    
    private void MostrarGrafoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MostrarGrafoActionPerformed
    if (this.arbol==null){
            JOptionPane.showMessageDialog(null, "No se ha cargado ningun archivo");
        }
        else{
        try{           
            Graph Familia = new MultiGraph("Familia Real");
            System.setProperty("org.graphstream.ui", "swing");
            System.setProperty("gs.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
            setFamilia(Familia);
            agregarPersonasAlGrafo(getArbol(),getFamilia());
            crearRelaciones(getFamilia());
            displayGraph(getFamilia());
        
        }
        catch(Exception err){
                    JOptionPane.showMessageDialog(null, err);
            }
        }
    }//GEN-LAST:event_MostrarGrafoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void BuscarAntepasadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarAntepasadosActionPerformed
        // TODO add your handling code here:
         String ante = Mant.getText();

    // Buscar la persona en el árbol genealógico
    Persona personaBuscada = buscarPersonaPorNombrePer(ante);

    if (personaBuscada == null) {
        JOptionPane.showMessageDialog(null, "Persona no encontrada.");
        return;
    }

    // Llamar al método que se encarga de crear el grafo y agregar los antepasados
    agregarAntepasadosAlGrafo(personaBuscada, ante);
    }//GEN-LAST:event_BuscarAntepasadosActionPerformed
    
     private void agregarAntepasadosAlGrafo(Persona persona, String ante) {
    // Crear un nuevo grafo para mostrar los antepasados
    Graph grafoAntepasados = new MultiGraph("Antepasados de " + ante);
    grafoAntepasados.setAttribute("ui.stylesheet", "node { fill-color: blue; size: 20px; }");

    // Crear el HashTable para almacenar las personas procesadas
    HashTable personasProcesadas = new HashTable();

    // Recursivamente agregar a la persona y sus antepasados directos al grafo
    agregarAntepasadosAlGrafoRecursivo(persona, grafoAntepasados, null, personasProcesadas);

    // Mostrar el grafo en una nueva ventana
    displayGraph(grafoAntepasados);
}

private void agregarAntepasadosAlGrafoRecursivo(Persona persona, Graph grafo, Node padre, HashTable personasProcesadas) {
    if (persona == null) return;  // Si la persona no existe, detener la recursión

    // Crear un identificador único para este nodo basado en la persona
    String nodoId = persona.getNombre() + persona.getNumeral();

    // Verificar si esta persona ya ha sido procesada
    if (personasProcesadas.containsKey(nodoId)) {
        return;  // Si ya fue procesada, detener la recursión
    }

    // Marcar esta persona como procesada
    personasProcesadas.put(nodoId, persona);

    // Crear el nodo si no existe
    if (grafo.getNode(nodoId) == null) {
        Node nodo = grafo.addNode(nodoId);
        nodo.setAttribute("ui.label", persona.getNombre() + " " + persona.getNumeral());
    }

    // Si existe un padre, agregar una arista entre el padre y este nodo
    if (padre != null) {
        String edgeId = padre.getId() + "-" + nodoId;
        if (grafo.getEdge(edgeId) == null) {
            grafo.addEdge(edgeId, padre.getId(), nodoId);
        }
    }
}
    
    private Persona buscarPersonaPorNombrePer(String nombre) {
    // Aquí debes implementar la lógica para buscar a la persona en el árbol
    // Puedes usar un recorrido en el árbol para encontrar la persona
    Persona persona = buscarPersonaEnArbol(getArbol(), nombre);
    if (persona != null) {
        System.out.println("Persona encontrada: " + persona.getNombre()); // Log de depuración
    } else {
        System.out.println("Persona no encontrada: " + nombre); // Log de depuración
    }
    return persona;
}
    
     private Persona buscarPersonaEnArbol(Arbol nodoActual, String nombre) {
    // Si el nodo actual es null, no hay nada que hacer
    if (nodoActual == null) {
        return null;
    }

    // Obtener la persona del nodo actual
    Persona personaActual = nodoActual.getValor();

    // Verificar si el nombre de la persona actual coincide con el nombre buscado
    if (personaActual.getNombre().equalsIgnoreCase(nombre)) {
        return personaActual; // Si coincide, retornar la persona
    }

    // Recursivamente buscar en los hijos
    Arbol hijoActual = nodoActual.getPrimerHijo(); // Obtener el primer hijo
    while (hijoActual != null) {
        Persona resultado = buscarPersonaEnArbol(hijoActual, nombre);
        if (resultado != null) {
            return resultado; // Si se encontró en algún hijo, retornar el resultado
        }
        hijoActual = hijoActual.getHermanoDerecho(); // Continuar con el siguiente hermano
    }

    // Si no se encontró la persona en ningún nodo del árbol, devolver null
    return null;
}
    
    // Función para agregar un nodo al grafo, guardando en un de sus atributo el objeto Persona de cada uno
    public static void agregarPersona(Graph grafo, Persona persona) {
        if (!persona.getNombre().equalsIgnoreCase("No children")){
            // Crear el nodo con el nombre completo
            String nombre = persona.getNombre();
            String numeral = persona.getNumeral();
            String mote = persona.getMote();
            Node nodo = grafo.addNode(nombre+numeral+mote);

            // Asignar el ui.label como "nombre (sin apellido), numeral"
            String uiLabel = null;
            String[] nombrediv= persona.getNombre().split(" ");
            String nombreSinApellido = null;
            if (nombrediv.length>2){
                nombreSinApellido = nombrediv[0]+ " " + nombrediv[1];               
            }
            else{
                nombreSinApellido = nombrediv[0];
            }
            if (esNumerico(numeral)){
                uiLabel = (nombreSinApellido);
            }
            else{
                uiLabel = (nombreSinApellido + " " + numeral);
            }
            
            // Asignar el estilo al nodo
            nodo.setAttribute("ui.style", estiloNodo);
            
            nodo.setAttribute("ui.label", uiLabel);

            // Guardar el objeto Persona como atributo del nodo
            nodo.setAttribute("persona", persona);
            
            // Establecer el estilo para las aristas (si es necesario)
            String estiloArista = 
            "size: 20px; " + 
            "fill-color: black;";
            //  Asigna el estilo y la arista al nodo
            grafo.setAttribute("ui.style", "edge {" + estiloArista + "}");
            
        }
    }
    /*Esta funcion se encarga de al recibir un graph con los nodos ya creados, revisa su atributo persona y busca el Id asociado a su nodo para despues buscar el nodo de sus hijos 
        y hacer una arista entre ellos.    */
    public void crearRelaciones(Graph grafo) {
    for (Node nodo : grafo) {
        // Obtener el objeto Persona del nodo actual con un cast explícito
        Persona persona = (Persona) nodo.getAttribute("persona");

        // Verificar que no tenga solo "No children"
        if (persona.getHijos() != null && !persona.getHijos()[0].equals("No children")) {
            for (String hijoNombre : persona.getHijos()) {
                Arbol nodoPersona = buscarNodoPorPersona(getArbol(), persona);
                if (nodoPersona != null){
                    String[] hijoH = buscarHijosPorNombre(nodoPersona, hijoNombre);
                if (hijoH != null) {                       
                    // Crear las aristas dirigidas desde el nodo actual a cada hijo
                    String nodoId = nodo.getId();

                    for (String hijo : hijoH) { 
                        // Crear un ID único para cada arista basada en el nodo actual y el nombre completo del hijo
                        String aristaId = nodoId + "-" + hijo;

                        // Verificar si la arista ya existe antes de agregarla
                        if (grafo.getEdge(aristaId) == null) {
                            grafo.addEdge(aristaId, nodoId, hijo, true); // Agregar la arista al grafo
       
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    //Al recibir un nombre y pasarle el nodo de su padre, se encarga de buscar en cada uno de sus hijos para ver si el nombre coincide con el buscado, si es asi 
    //retorna un string de el nombre de la persona mas el numeral mas el mote, el cual es la forma en la que se guardan los Id de cada nodo
    public String[] buscarHijosPorNombre(Arbol nodoPadre, String nombreHijo) {
    // Primera pasada: Contar cuántos hijos coinciden
    int contador = 0;
    Arbol hijoActual = nodoPadre.getPrimerHijo();
    while (hijoActual != null) {
        Persona hijoPersona = hijoActual.getValor();
        String nombreHijoActual = hijoPersona.getNombre().split(" ")[0]; // Obtener solo el nombre sin apellido
        if (nombreHijoActual.equals(nombreHijo)) {
            contador++;
        }
        hijoActual = hijoActual.getHermanoDerecho();
    }

    // Si no se encontraron coincidencias, devolver null
    if (contador == 0) {
        return null;
    }

    // Segunda pasada: Almacenar los nombres en un arreglo
    String[] resultado = new String[contador];
    int indice = 0;
    hijoActual = nodoPadre.getPrimerHijo();
    while (hijoActual != null) {
        Persona hijoPersona = hijoActual.getValor();
        String nombreHijoActual = hijoPersona.getNombre().split(" ")[0];
        if (nombreHijoActual.equals(nombreHijo)) {
            resultado[indice] = hijoPersona.getNombre() + hijoPersona.getNumeral() + hijoPersona.getMote();
            indice++;
        }
        hijoActual = hijoActual.getHermanoDerecho();
    }

    return resultado;
    }
    
    /*Esta funcion se encarga de revisar en un arbol si la perosna buscada se encuentra en este arbol, revisando si su nombre y numeral coincide, en caso de que coincidan
    retorna el nodo del arbol en el que esta guardado de esa persona    
    */
    public Arbol buscarNodoPorPersona(Arbol nodoActual, Persona personaBuscada) {
    // Si el nodo actual es null, no hay nada que hacer
    if (nodoActual == null) {
        return null;
    }

    // Obtener la persona del nodo actual
    Persona personaActual = nodoActual.getValor();

    // Si la persona del nodo actual coincide con la persona buscada, devolver el nodo
    if (personaActual.getNombre().equals(personaBuscada.getNombre()) && personaActual.getNumeral().equals(personaBuscada.getNumeral()) ) {
        return nodoActual;
    }

    // Recursivamente buscar en los hijos del nodo actual
    Arbol hijoActual = nodoActual.getPrimerHijo();
    while (hijoActual != null) {
        Arbol resultado = buscarNodoPorPersona(hijoActual, personaBuscada);
        if (resultado != null) {
            return resultado; // Si la persona fue encontrada en algún hijo
        }
        hijoActual = hijoActual.getHermanoDerecho(); // Continuar con el siguiente hermano
    }

    // Si no se encuentra la persona en ningún nodo del árbol, devolver null
    return null;
    }
    
    //funcion que se encarga de recorrer todo el arbol y en cada nodo no nulo de el llama a la funcion agregarPersonas
    public static void agregarPersonasAlGrafo(Arbol nodoActual, Graph grafo) {
    // Si el nodo actual es null, no hacer nada
    if (nodoActual == null) {
        return;
    }

    // Obtener la persona asociada a este nodo
    Persona persona = nodoActual.getValor();

    // Llamar a la función para agregar el nodo al grafo
    agregarPersona(grafo, persona);

    // Recursivamente llamar para agregar los hijos
    Arbol hijoActual = nodoActual.getPrimerHijo(); // Obtener el primer hijo
    while (hijoActual != null) {
        // Llamar recursivamente para agregar el hijo al grafo
        agregarPersonasAlGrafo(hijoActual, grafo);
        hijoActual = hijoActual.getHermanoDerecho(); // Pasar al siguiente hermano
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BuscarAntepasados;
    private javax.swing.JButton CargaArchivo;
    private javax.swing.JTextField Mant;
    private javax.swing.JButton MostrarGrafo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the CargaArchivo
     */
    public javax.swing.JButton getCargaArchivo() {
        return CargaArchivo;
    }

    /**
     * @return the MostrarGrafo
     */
    public javax.swing.JButton getMostrarGrafo() {
        return MostrarGrafo;
    }

    /**
     * @return the jButton1
     */
    public javax.swing.JButton getjButton1() {
        return jButton1;
    }

    /**
     * @return the jPanel1
     */
    public javax.swing.JPanel getjPanel1() {
        return jPanel1;
    }

    /**
     * @param aFamilia the familia to set
     */
    public static void setFamilia(Graph aFamilia) {
        familia = aFamilia;
    }

    /**
     * @return the mySlylesheet
     */
    public static String getEstiloNodo() {
        return estiloNodo;
    }
    public static boolean esNumerico(String str) {
    try {
        Integer.parseInt(str.trim()); // Para números enteros
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}
    
    
}
