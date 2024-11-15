/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

import Compilador.ParserSym;

/**
 *
 * @author alex
 */
public abstract class Nodo {

    private String valor;
    private TipoNodo tipoNodo;
    protected static int numNodos = 0;
    private int numNodo;

    public Nodo(String valor, TipoNodo tipoNodo) {
        this.valor = valor;
        this.tipoNodo = tipoNodo;
        numNodos++;
        numNodo = numNodos;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public TipoNodo getTipoNodo() {
        return tipoNodo;
    }

    public void setTipoNodo(TipoNodo tipoNodo) {
        this.tipoNodo = tipoNodo;
    }

    public int getNumNodo() {
        return numNodo;
    }

    public void setNumNodo(int numNodo) {
        this.numNodo = numNodo;
    }

    public abstract String getGrafica();

}
