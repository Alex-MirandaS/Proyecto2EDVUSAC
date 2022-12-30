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
public class Dato {

    private String valor;
    private ParserSym tipoDato;

    public Dato(String valor, ParserSym tipoDato) {
        this.valor = valor;
        this.tipoDato = tipoDato;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public ParserSym getTipoDato() {
        return tipoDato;
    }

    public void setTipoDato(ParserSym tipoDato) {
        this.tipoDato = tipoDato;
    }

}
