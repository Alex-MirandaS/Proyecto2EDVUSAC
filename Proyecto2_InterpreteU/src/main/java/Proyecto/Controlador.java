/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyecto;

import Objetos.Hoja;
import Objetos.Nodo;
import Objetos.Padre;
import Objetos.TipoNodo;
import java.util.ArrayList;

/**
 *
 * @author alex
 */
public class Controlador {

    public ArrayList<Nodo> recDer(ArrayList<Nodo> arreglo, Nodo nodo) {
        arreglo.add(0, nodo);
        return arreglo;
    }

    public ArrayList<Nodo> finProd(Nodo nodo) {
        ArrayList<Nodo> temp = new ArrayList<>();
        temp.add(nodo);
        return temp;
    }

    public ArrayList<Nodo> recDerParam(String td, String id, ArrayList<Nodo> arreglo) {
        arreglo.add(0, new Hoja(id, TipoNodo.IDENTIFICADOR));
        arreglo.add(0, new Hoja(td, TipoNodo.TIPODATO));
        return arreglo;
    }

    public ArrayList<Nodo> finProdParam(String td, String id) {
        ArrayList<Nodo> temp = new ArrayList<>();
        temp.add(0, new Hoja(td, TipoNodo.TIPODATO));
        temp.add(0, new Hoja(id, TipoNodo.IDENTIFICADOR));
        return temp;
    }

    public Padre newAsig(String td, ArrayList<Nodo> arreglo, Nodo ex) {
        ArrayList<Nodo> temp = new ArrayList<>();
        if (td != null) {
            temp.add(new Hoja((String) td, TipoNodo.TIPODATO));
        }
        temp.addAll(arreglo);
        if (ex != null) {
            ArrayList<Nodo> nda = new ArrayList();
            nda.add(temp.get(temp.size() - 1));
            nda.add(new Hoja("=", TipoNodo.ASIGN));
            nda.add(ex);
            temp.add(new Padre("Asignación", TipoNodo.ASIGNACION, nda));
            temp.remove(temp.size() - 2);
        }
        return new Padre("Asignación", TipoNodo.ASIGNACION, temp);
    }

    public Padre newArr(String td1, String id, String td2, ArrayList<Nodo> parametros, Nodo ex, Nodo ex2) {

        ArrayList<Nodo> temp = new ArrayList<>();
        if (td1 != null) {
            temp.add(new Hoja(td1, TipoNodo.TIPODATO));
            temp.add(new Hoja(id, TipoNodo.IDENTIFICADOR));
            temp.add(new Hoja("[", TipoNodo.SIMBOLO));
            temp.add(new Hoja("]", TipoNodo.SIMBOLO));
            temp.add(new Hoja("=", TipoNodo.IGUAL));
            if (td2 != null) {
                temp.add(new Hoja("new", TipoNodo.NEW));
                temp.add(new Hoja(td2, TipoNodo.TIPODATO));
                temp.add(new Hoja("[", TipoNodo.SIMBOLO));
                temp.add(ex);
                temp.add(new Hoja("]", TipoNodo.SIMBOLO));
            } else {
                temp.add(new Hoja("{", TipoNodo.SIMBOLO));
                temp.addAll(parametros);
                temp.add(new Hoja("}", TipoNodo.SIMBOLO));
            }
        } else {
            temp.add(new Hoja(id, TipoNodo.IDENTIFICADOR));
            temp.add(new Hoja("[", TipoNodo.SIMBOLO));
            temp.add(ex);
            temp.add(new Hoja("]", TipoNodo.SIMBOLO));
            temp.add(new Hoja("=", TipoNodo.IGUAL));
            temp.add(ex2);
        }
        return new Padre("Asignación", TipoNodo.ASIGNACION, temp);
    }

    public Padre newExp(Nodo ex1, TipoNodo tipo, Nodo ex2, String id) {
        ArrayList<Nodo> temp = new ArrayList<>();
        if (ex2 != null) {
            Hoja tempHoja = null;
            switch (tipo) {
                case MAS:
                    tempHoja = new Hoja("+", tipo);
                    break;
                case MENOS:
                    tempHoja = new Hoja("-", tipo);
                    break;
                case MULTI:
                    tempHoja = new Hoja("*", tipo);
                    break;
                case DIV:
                    tempHoja = new Hoja("/", tipo);
                    break;
                case POT:
                    tempHoja = new Hoja("^", tipo);
                    break;
                case MOD:
                    tempHoja = new Hoja("%", tipo);
                    break;
            }
            temp.add(ex1);
            temp.add(tempHoja);
            temp.add(ex2);
        } else {
            switch (tipo) {
                case NEG:
                    temp.add(new Hoja("-", tipo));
                    temp.add(ex1);
                    break;
                case PAR:
                    temp.add(new Hoja("(", tipo));
                    temp.add(ex1);
                    temp.add(new Hoja(")", tipo));
                    break;
                case ARR:
                    temp.add(new Hoja(id, TipoNodo.IDENTIFICADOR));
                    temp.add(new Hoja("[", tipo));
                    temp.add(ex1);
                    temp.add(new Hoja("]", tipo));
            }
        }

        return new Padre("Expresion", TipoNodo.EXPRESION, temp);
    }

    public Padre newIncr(String id, String sim, boolean idPrim) {

        ArrayList<Nodo> temp = new ArrayList<>();
        if (idPrim) {
            temp.add(new Hoja(id, TipoNodo.IDENTIFICADOR));
            temp.add(new Hoja(sim, TipoNodo.INCR));
        } else {
            temp.add(new Hoja(sim, TipoNodo.INCR));
            temp.add(new Hoja(id, TipoNodo.IDENTIFICADOR));
        }
        return new Padre("Incremento/Decremento", TipoNodo.INCR, temp);
    }

    public Padre newTer(String td, ArrayList<Nodo> ids, Nodo c, Nodo ex1, Nodo ex2) {
        ArrayList<Nodo> temp = new ArrayList<>();
        if (td != null) {
            temp.add(new Hoja(td, TipoNodo.TIPODATO));
        }
        temp.addAll(ids);
        temp.add(new Hoja("=", TipoNodo.IGUAL));
        temp.add(c);
        temp.add(new Hoja("?", TipoNodo.TERNARIO));
        temp.add(ex1);
        temp.add(new Hoja(":", TipoNodo.DOSPUNTOS));
        temp.add(ex2);
        return new Padre("Ternario", TipoNodo.TERNARIO, temp);
    }

    public Padre newCond(Nodo ex1, TipoNodo tipo, Nodo ex2) {
        ArrayList<Nodo> temp = new ArrayList<>();
        Hoja tempHoja = null;
        if (ex2 != null) {
            switch (tipo) {
                case IGUAL:
                    tempHoja = new Hoja("==", tipo);
                    break;
                case DESIG:
                    tempHoja = new Hoja("!=", tipo);
                    break;
                case MENORQ:
                    tempHoja = new Hoja("<", tipo);
                    break;
                case MAYORQ:
                    tempHoja = new Hoja(">", tipo);
                    break;
                case MENORI:
                    tempHoja = new Hoja("<=", tipo);
                    break;
                case MAYORI:
                    tempHoja = new Hoja(">=", tipo);
                    break;
                case AND:
                    tempHoja = new Hoja("&&", tipo);
                    break;
                case OR:
                    tempHoja = new Hoja("||", tipo);
                    break;
            }
            temp.add(ex1);
            temp.add(tempHoja);
            temp.add(ex2);
        } else {
            tempHoja = new Hoja("!", tipo);
            temp.add(tempHoja);
            temp.add(ex1);
        }

        return new Padre("Condición", TipoNodo.CONDICION, temp);
    }

    public Padre newLlaMet(String id, ArrayList<Nodo> paramList) {
        ArrayList<Nodo> temp = new ArrayList<>();
        temp.add(new Hoja(id, TipoNodo.IDENTIFICADOR));
        temp.add(new Hoja("(", TipoNodo.SIMBOLO));
        if (paramList != null) {
            temp.addAll(paramList);
        }
        temp.add(new Hoja(")", TipoNodo.SIMBOLO));
        return new Padre("Condición", TipoNodo.CONDICION, temp);
    }

    public Padre newBCR(TipoNodo tipo, Nodo expr) {
        ArrayList<Nodo> temp = new ArrayList<>();
        switch (tipo) {
            case BRK:
                temp.add(new Hoja("break", TipoNodo.BRK));
                break;
            case CONT:
                temp.add(new Hoja("continue", TipoNodo.CONT));
                break;
            default:
                temp.add(new Hoja("return", TipoNodo.RTRN));
                if (expr != null) {
                    temp.add(expr);
                }
        }
        return new Padre("BREAK/CONTINUE/RETURN", TipoNodo.BCR, temp);
    }

    public Padre newWrtLn(Nodo ex) {
        ArrayList<Nodo> temp = new ArrayList<>();
        temp.add(new Hoja("WriteLine", TipoNodo.WrtLn));
        temp.add(new Hoja("(", TipoNodo.SIMBOLO));
        temp.add(ex);
        temp.add(new Hoja(")", TipoNodo.SIMBOLO));
        return new Padre("WriteLine", TipoNodo.WrtLn, temp);
    }

    public Padre newMet(String id, ArrayList<Nodo> param, ArrayList<Nodo> sentencias) {
        ArrayList<Nodo> temp = new ArrayList<>();
        temp.add(new Hoja("void", TipoNodo.VOID));
        temp.add(new Hoja(id, TipoNodo.IDENTIFICADOR));
        temp.add(new Hoja("(", TipoNodo.SIMBOLO));
        if (param != null) {
            temp.addAll(param);
        }
        temp.add(new Hoja(")", TipoNodo.SIMBOLO));
        temp.add(new Hoja("{", TipoNodo.SIMBOLO));
        if (sentencias != null) {
            temp.add(new Padre("Sentencias", TipoNodo.SENT, sentencias));
        }
        temp.add(new Hoja("}", TipoNodo.SIMBOLO));

        return new Padre("Metodo", TipoNodo.MTD, temp);
    }

    public Padre newFun(String td, String id, ArrayList<Nodo> param, ArrayList<Nodo> sentencias) {
        ArrayList<Nodo> temp = new ArrayList<>();
        temp.add(new Hoja(td, TipoNodo.TIPODATO));
        temp.add(new Hoja(id, TipoNodo.IDENTIFICADOR));
        temp.add(new Hoja("(", TipoNodo.SIMBOLO));
        if (param != null) {
            temp.addAll(param);
        }
        temp.add(new Hoja(")", TipoNodo.SIMBOLO));
        temp.add(new Hoja("{", TipoNodo.SIMBOLO));
        if (sentencias != null) {
            temp.add(new Padre("Sentencias", TipoNodo.SENT, sentencias));
        }
        temp.add(new Hoja("}", TipoNodo.SIMBOLO));

        return new Padre("Metodo", TipoNodo.MTD, temp);
    }

    public Padre newIf(Nodo c, ArrayList<Nodo> sentencias, Nodo elif) {
        ArrayList<Nodo> temp = new ArrayList<>();
        temp.add(new Hoja("if", TipoNodo.IF));
        temp.add(new Hoja("(", TipoNodo.SIMBOLO));
        temp.add(c);
        temp.add(new Hoja(")", TipoNodo.SIMBOLO));
        temp.add(new Hoja("{", TipoNodo.SIMBOLO));
        temp.add(new Padre("Sentencias", TipoNodo.SENT, sentencias));
        temp.add(new Hoja("}", TipoNodo.SIMBOLO));
        if (elif != null) {
            temp.add(elif);
        }
        return new Padre("IF", TipoNodo.IF, temp);
    }

    public Padre newElIf(Nodo c, ArrayList<Nodo> sentencias, Nodo elif) {
        ArrayList<Nodo> temp = new ArrayList<>();
        temp.add(new Hoja("else", TipoNodo.IF));
        if (c != null) {
            temp.add(new Hoja("if", TipoNodo.IF));
            temp.add(new Hoja("(", TipoNodo.SIMBOLO));
            temp.add(c);
            temp.add(new Hoja(")", TipoNodo.SIMBOLO));
        }
        temp.add(new Hoja("{", TipoNodo.SIMBOLO));
        temp.add(new Padre("Sentencias", TipoNodo.SENT, sentencias));
        temp.add(new Hoja("}", TipoNodo.SIMBOLO));
        if (elif != null) {
            temp.add(elif);
        }
        return new Padre("ELSE/ELSE_IF", TipoNodo.ELIF, temp);
    }

    public Padre newSwt(Nodo ex, Nodo c) {
        ArrayList<Nodo> temp = new ArrayList<>();
        temp.add(new Hoja("switch", TipoNodo.SWT));
        temp.add(new Hoja("(", TipoNodo.SIMBOLO));
        temp.add(ex);
        temp.add(new Hoja(")", TipoNodo.SIMBOLO));
        temp.add(new Hoja("{", TipoNodo.SIMBOLO));
        temp.add(c);
        temp.add(new Hoja("}", TipoNodo.SIMBOLO));
        return new Padre("SWITCH", TipoNodo.SWT, temp);
    }

    public Padre newCas(Nodo ex, ArrayList<Nodo> sentencias, Nodo c) {
        ArrayList<Nodo> temp = new ArrayList<>();
        if (ex != null) {
            temp.add(new Hoja("case", TipoNodo.CASE));
            temp.add(ex);
            temp.add(new Hoja(":", TipoNodo.DOSPUNTOS));
            temp.add(new Padre("Sentencias", TipoNodo.SENT, sentencias));
            if (c != null) {
                temp.add(c);
            }
            return new Padre("CASE", TipoNodo.CASE, temp);
        } else {
            temp.add(new Hoja("default", TipoNodo.BCR));
            temp.add(new Hoja(":", TipoNodo.DOSPUNTOS));
            temp.add(new Padre("Sentencias", TipoNodo.SENT, sentencias));

            return new Padre("DEFAULT", TipoNodo.DEF, temp);
        }
    }

    public Padre newWhil(Nodo c, ArrayList<Nodo> sentencias) {
        ArrayList<Nodo> temp = new ArrayList<>();
        temp.add(new Hoja("while", TipoNodo.WHILE));
        temp.add(new Hoja("(", TipoNodo.SIMBOLO));
        temp.add(c);
        temp.add(new Hoja(")", TipoNodo.SIMBOLO));
        temp.add(new Hoja("{", TipoNodo.SIMBOLO));
        temp.add(new Padre("Sentencias", TipoNodo.SENT, sentencias));
        temp.add(new Hoja("}", TipoNodo.SIMBOLO));
        return new Padre("WHILE", TipoNodo.WHILE, temp);
    }

}
