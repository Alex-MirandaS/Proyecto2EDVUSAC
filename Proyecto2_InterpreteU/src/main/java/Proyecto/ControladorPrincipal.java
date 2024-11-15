/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyecto;

import Compilador.Lexer;
import Compilador.Parser;
import GUI.TablaResultados;
import Objetos.*;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.lang.System.Logger;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alex
 */
public class ControladorPrincipal {

    private Principal principal;

    private String pathDocumentoActual;
    private Lexer lexer;
    private Parser parser;
    private Nodo raiz;
    private boolean conservarTexto = true;
    private boolean esperar = true;

    ArrayList<ErrorLSS> errores;
    ArrayList<Simbolos> tablaDeSimbolos;

    public ControladorPrincipal(Principal principal) {
        this.principal = principal;
    }

    private boolean areaTextoVacía() {
        if (!principal.getIu().getAreaEditor().getText().equals("")) {
            return false;
        } else {
            return true;
        }
    }

    public void guardarCambios(boolean eleccion) {
        conservarTexto = eleccion;
    }

    private void verificarCambiosArchivo() {
        if (!areaTextoVacía()) {
            principal.getGuardar().setVisible(true);
        }
    }

    public void nuevoArchivo() {
        verificarCambiosArchivo();
        if (conservarTexto) {
            guardarArchivo(principal.getIu().getAreaEditor().getText());
            pathDocumentoActual = "";
            principal.getIu().getAreaEditor().setText("");
        }
    }

//Se encarga de seleecionar el archivo y realizar el proceso correspondiente de extraccion de texto del mismo.
    public ArrayList<String> seleccionarArchivo() {
        ArrayList<String> filasArchivo = new ArrayList<>();
        verificarCambiosArchivo();

//        if (conservarTexto) {
//            if (!pathDocumentoActual.equals("")) {
//                guardarArchivo(principal.getPrincipalGUI().getAreaTexto().getText());
//            } else {
//                guardarArchivoComo();
//            }
//
//        }
        JFileChooser fileChosser = new JFileChooser();
        int seleccion = fileChosser.showOpenDialog(principal.getIu());

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            //aqui selecciono y guardo el FILE que va a utilizar el FileReader
            File fichero = fileChosser.getSelectedFile();
            try {
                filasArchivo = principal.getLec().leerFichero(fichero);
                principal.getIu().getAreaEditor().setText("");
                mostrarEnTextArea(filasArchivo, principal.getIu().getAreaEditor());
                pathDocumentoActual = fichero.getAbsolutePath();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al leer el archivo");
            }
        }

        return filasArchivo;
    }
//Se encarga de guardar un archivo y reiniciar el area de texto, para volver a abrir el archivo

    public void guardarArchivo(String texto) {
        try {
            //principal.getPrincipalGUI().getAreaTexto().getText()

            principal.getEsc().guardarArchivoTexto(texto);
            JOptionPane.showMessageDialog(null, "ARCHIVO GUARDADO, BUSQUELO EN LA CARPETA: archivos");
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Se encarga de guardar un archivo y reiniciar el area de texto, para volver a abrir el archivo
    public void guardarArchivoComo() {

        try {
            principal.getEsc().guardarArchivoTexto(principal.getIu().getAreaEditor().getText());
            JOptionPane.showMessageDialog(null, "ARCHIVO GUARDADO, SE NECESITA VOLVER A ABRIRLO");
            principal.getIu().getAreaEditor().setText("");
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
//Se encarga de agregar al area de texto, todo el texto ordenado, correspondiente al texto de entrada

    public void mostrarEnTextArea(ArrayList<String> array, JTextArea areaTexto) {
        areaTexto.setText("");
        for (int i = 0; i < array.size(); i++) {
            areaTexto.append(array.get(i));
            areaTexto.append(System.getProperty("line.separator"));
        }
    }

    //Se encarga de ejecutar el parser con el texto ingresado en el TextArea
    public void ejecutar() throws Exception {
        String xd;
        if (!principal.getIu().getAreaEditor().getText().equals("")) {
            String texto = principal.getIu().getAreaEditor().getText();
            try {
                lexer = new Lexer(new StringReader(texto));
                parser = new Parser(lexer);
                xd = "" + parser.parse();
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "SE HALLARON ERRORES, POR FAVOR, CORRIGALOS ANTES DE CONTINUAR");
            }

            if (parser.getErrores().isEmpty() && lexer.getErrores().isEmpty() && parser.getControl().getErroresSemanticos().isEmpty()) {

            } else {
                JOptionPane.showMessageDialog(null, "SE HALLARON ERRORES, POR FAVOR, CORRIGALOS ANTES DE CONTINUAR");
            }

            raiz = new Padre("RAIZ", TipoNodo.RAIZ, parser.getNodos());
            mostrarEnTextArea(parser.getControl().getTexto(), principal.getIu().getAreaConsola());
            parser.getErrores().addAll(lexer.getErrores());
            parser.getErrores().addAll(parser.getControl().getErroresSemanticos());
            errores = parser.getErrores();
            tablaDeSimbolos = parser.getControl().gettSimbolos();
            crearAST();
        }
    }

    private void crearAST() {
        if (raiz != null) {
            try {
                principal.getEsc().guardarGraphiz(getArbol());
                ProcessBuilder proceso;
                proceso = new ProcessBuilder("dot", "-Tsvg", "-o", "archivos/archivo.svg", "archivos/archivo.dot");
                proceso.redirectErrorStream(true);
                proceso.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public String getArbol() {
        String temp = "digraph L{\n"
                + "     subgraph instrucciones\n"
                + "     {\n"
                + "     label=\"RAIZ\"\n" + "nodo" + raiz.getNumNodo() + ";\n" + raiz.getGrafica() + "}\n}";
        return temp;
    }

//Se encarga de mostrar los reportes, si existe algun error o si el archivo esta libre de errores
//
//    public void mostrarReportes() {
//        verificarTokens();
//        for (int i = 0; i < tokens.size(); i++) {
//            if (tokens.get(i).getTipo().equals(TipoToken.ERROR)) {
//                principal.getReportesGUI().getrErrores().setEnabled(true);
//                principal.getReportesGUI().getrLexemas().setEnabled(false);
//                principal.getReportesGUI().getrTokens().setEnabled(false);
//                principal.getReportesGUI().getAnalisisSintactico().setEnabled(false);
//                break;
//            }
//            principal.getReportesGUI().getrErrores().setEnabled(false);
//            principal.getReportesGUI().getrLexemas().setEnabled(true);
//            principal.getReportesGUI().getrTokens().setEnabled(true);
//            principal.getReportesGUI().getAnalisisSintactico().setEnabled(true);
//        }
//        principal.getReportesGUI().setVisible(true);
//    }
//Se encarga de abrir el reporte de errores del archivo seleccionado
    public void abrirReporteErrores() {

        TablaResultados tabla = new TablaResultados();
        DefaultTableModel modelo = new DefaultTableModel();
        tabla.getTabla().setModel(modelo);
        modelo.addColumn("LINEA");
        modelo.addColumn("COLUMNA");
        modelo.addColumn("TIPO");
        modelo.addColumn("DESCRIPCION");

        for (int i = 0; i < errores.size(); i++) {
            modelo.addRow(new Object[]{errores.get(i).getLinea(), errores.get(i).getColumna(), errores.get(i).getTipoError(), errores.get(i).getLexema()});
        }
        tabla.setVisible(true);
    }
//Se encarga de abrir el reporte de tokens del archivo seleccionado

    public void abrirTablaSimbolos() {
        TablaResultados tabla = new TablaResultados();
        DefaultTableModel modelo = new DefaultTableModel();
        tabla.getTabla().setModel(modelo);
        modelo.addColumn("ID");
        modelo.addColumn("TIPO");
        modelo.addColumn("VALOR");

        for (int i = 0; i < tablaDeSimbolos.size(); i++) {
            modelo.addRow(new Object[]{tablaDeSimbolos.get(i).getId(), tablaDeSimbolos.get(i).getTipo(), tablaDeSimbolos.get(i).getValor()});
        }
        tabla.setVisible(true);
    }

    public void graficar() {
        String txt = "digraph G\n"
                + "{\n"
                + "      node[shape = cicle];\n"
                + "      node[style = filled];\n"
                + "      node[fillcolor = \"#EEEEE\"];\n"
                + "      node[color = \"#EEEEE\"];\n"
                + "      edge[color = \"#31CEFO\"];\n";
        if (raiz != null) {
            //txt += raiz.graficaTxt();
        }
        txt += "}\n";
        //return txt;
    }

    public ArrayList<ErrorLSS> getErrores() {
        return errores;
    }

    public void setErrores(ArrayList<ErrorLSS> errores) {
        this.errores = errores;
    }

    public ArrayList<Simbolos> getTablaDeSimbolos() {
        return tablaDeSimbolos;
    }

    public void setTablaDeSimbolos(ArrayList<Simbolos> tablaDeSimbolos) {
        this.tablaDeSimbolos = tablaDeSimbolos;
    }

}
