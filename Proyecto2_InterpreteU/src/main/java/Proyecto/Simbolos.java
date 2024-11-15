/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyecto;

import Objetos.TipoNodo;

/**
 *
 * @author alex
 */
public class Simbolos {
    
    private String id, valor;
    private TipoNodo tipo;

    public Simbolos(String id, TipoNodo tipo, String valor) {
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TipoNodo getTipo() {
        return tipo;
    }

    public void setTipo(TipoNodo tipo) {
        this.tipo = tipo;
    }
    
    
}
