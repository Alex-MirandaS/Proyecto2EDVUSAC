/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

import Compilador.ParserSym;
import java.util.ArrayList;

/**
 *
 * @author alex
 */
public class Padre extends Nodo {

    private ArrayList<Nodo> hijos;

    public Padre(String valor, TipoNodo tipo, ArrayList<Nodo> hijos) {
        super(valor, tipo);
        this.hijos = hijos;
    }

    public ArrayList<Nodo> getHijos() {
        return hijos;
    }

    public void setHijos(ArrayList<Nodo> hijos) {
        this.hijos = hijos;
    }

    @Override
    public String getGrafica() {
        String graf = "nodo" + getNumNodo() + "[label=\"" + getValor() + "\"] ;\n";
        if (hijos != null && !hijos.isEmpty()) {
            for (Nodo hijo : hijos) {
                graf += "nodo" + getNumNodo() + " -> " + "nodo" + hijo.getNumNodo() + "\n" + hijo.getGrafica();
            }
        }
        return graf;
    }

}
