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
public class Expresion extends Padre {

    private Hoja result;

    public Expresion(Hoja result, String valor, TipoNodo tipo, ArrayList<Nodo> hijos) {
        super(valor, tipo, hijos);
        this.result = result;
        //String hola = ""+(5+5);
    }

    public Hoja getResult() {
        return result;
    }

    public void setResult(Hoja dato) {
        this.result = result;
    }

}
