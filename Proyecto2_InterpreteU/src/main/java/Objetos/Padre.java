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

}
