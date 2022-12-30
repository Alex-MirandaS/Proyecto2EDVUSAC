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

    String valor;
    TipoNodo tipoNodo;

    public Nodo(String valor, TipoNodo tipoNodo) {
        this.valor = valor;
        this.tipoNodo = tipoNodo;
    }

}
