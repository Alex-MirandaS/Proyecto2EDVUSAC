/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyecto;

import Archivos.EscritorArchivosTexto;
import Archivos.LectorArchivosEnTexto;
import GUI.GuardarCambiosGUI;
import GUI.PrincipalGUI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author alex
 */
public class Principal {

    //GUI
    private PrincipalGUI iu = new PrincipalGUI(this);
    private GuardarCambiosGUI guardar = new GuardarCambiosGUI(this);
    //CLASES
    private LectorArchivosEnTexto lec = new LectorArchivosEnTexto();
    private EscritorArchivosTexto esc = new EscritorArchivosTexto();
    private ControladorPrincipal control = new ControladorPrincipal(this);
    //COMPILADOR

    public void iniciar() {
        iu.setExtendedState(JFrame.MAXIMIZED_BOTH);
        iu.setVisible(true);
    }

    public void cargarArchivo() {
        control.seleccionarArchivo();

    }

    public void guardarArchivo() {
        control.guardarArchivo(iu.getAreaConsola().getText());
    }

    public void nuevoArchivo() {
        control.nuevoArchivo();
    }

    public void traducir() {
        try {
            control.ejecutar();
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setEsc(EscritorArchivosTexto esc) {
        this.esc = esc;
    }

    public void verificarErrores() {
        if (control.getErrores() != null && control.getErrores().size() != 0) {
            control.abrirReporteErrores();
        }
    }

    public void verificarTSimbolos() {
        if (control.getTablaDeSimbolos() != null && control.getTablaDeSimbolos().size() != 0) {
            control.abrirTablaSimbolos();
        }
    }

    public PrincipalGUI getIu() {
        return iu;
    }

    public void setIu(PrincipalGUI iu) {
        this.iu = iu;
    }

    public GuardarCambiosGUI getGuardar() {
        return guardar;
    }

    public void setGuardar(GuardarCambiosGUI guardar) {
        this.guardar = guardar;
    }

    public LectorArchivosEnTexto getLec() {
        return lec;
    }

    public void setLec(LectorArchivosEnTexto lec) {
        this.lec = lec;
    }

    public ControladorPrincipal getControl() {
        return control;
    }

    public void setControl(ControladorPrincipal control) {
        this.control = control;
    }

    public EscritorArchivosTexto getEsc() {
        return esc;
    }

}
