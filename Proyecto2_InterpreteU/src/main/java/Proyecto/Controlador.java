/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyecto;

import Objetos.ErrorLSS;
import Objetos.Expresion;
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

    private ArrayList<Simbolos> tSimbolos = new ArrayList();
    private ArrayList<ErrorLSS> erroresSemanticos = new ArrayList<>();
    private ArrayList<String> texto = new ArrayList<>();

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
            nda.add(ex);
            temp.add(new Padre("Asignación", TipoNodo.ASIGNACION, nda));
            temp.remove(temp.size() - 2);
        }
        agTabSim(arreglo, ex);
        return new Padre("Asignación", TipoNodo.ASIGNACION, temp);
    }

    public Padre newArr(String td1, String id, String td2, ArrayList<Nodo> parametros, Nodo ex, Nodo ex2) {

        ArrayList<Nodo> temp = new ArrayList<>();
        if (td1 != null) {
            temp.add(new Hoja(td1, TipoNodo.TIPODATO));
            temp.add(new Hoja(id, TipoNodo.IDENTIFICADOR));
            if (td2 != null) {

            } else {
                temp.addAll(parametros);
            }
        } else {
            temp.add(new Hoja(id, TipoNodo.IDENTIFICADOR));
            temp.add(ex2);
        }
        return new Padre("Asignación", TipoNodo.ASIGNACION, temp);
    }

    public Padre newExp(Nodo ex1, TipoNodo tipo, Nodo ex2, String id) {
        ArrayList<Nodo> temp = new ArrayList<>();
        Hoja hTemp = null;
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
                    temp.add(ex1);
                    break;
                case ARR:
                    temp.add(new Hoja(id, TipoNodo.IDENTIFICADOR));
                    break;
            }
        }
        hTemp = calcularExpresion(ex1, tipo, ex2);//verificar
        return new Expresion(hTemp, "Expresion", TipoNodo.EXPRESION, temp);
    }

    private Nodo convertidor(Nodo n) {
        if (n != null) {
            if (n.getTipoNodo().equals(TipoNodo.BOOLEAN)) {
                String dato = "";
                if (n.getValor().equalsIgnoreCase("true")) {
                    dato = "1";
                } else {
                    dato = "0";
                }
                return new Hoja(dato, TipoNodo.INT);
            } else if (n.getTipoNodo().equals(TipoNodo.CHAR)) {
                return new Hoja("" + ((int) n.getValor().charAt(0)), TipoNodo.INT);
            }
        }
        return n;
    }

    private Nodo camExpr(Nodo ex) {
        if (ex != null) {
            if (ex.getTipoNodo().equals(TipoNodo.EXPRESION)) {
                return ((Expresion) ex).getResult();
            } else if (ex.getTipoNodo().equals(TipoNodo.ID)) {
                return busTaSim(ex);
            } else {
                return ex;
            }
        }
        return ex;
    }

    private Hoja calcularExpresion(Nodo ex1, TipoNodo tipo, Nodo ex2) {
        Hoja temp = null;
        ex1 = camExpr(ex1);
        ex2 = camExpr(ex2);
        switch (tipo) {

            case MAS:
                if (!verificarErrorSeman(ex1, tipo, ex2)) {
                    if (esVal(ex1, TipoNodo.STRING) || esVal(ex2, TipoNodo.STRING)
                            || (esVal(ex1, TipoNodo.CHAR) && esVal(ex2, TipoNodo.CHAR))) {
                        temp = new Hoja(concatenar(ex1, ex2), TipoNodo.STRING);
                    } else {
                        convertidor(ex1);
                        convertidor(ex2);//preguntar
                        if (esVal(ex1, TipoNodo.INT) && esVal(ex2, TipoNodo.INT)) {
                            temp = new Hoja("" + (int) calcu(ex1, tipo, ex2), TipoNodo.INT);
                        } else {
                            temp = new Hoja("" + calcu(ex1, tipo, ex2), TipoNodo.DOUBLE);
                        }
                    }
                }
                break;
            case PAR:
                temp = (Hoja) ex1;
                break;
            case ARR:
            //NADA
            default:
                if (!verificarErrorSeman(ex1, tipo, ex2)) {
                    convertidor(ex1);
                    convertidor(ex2);
                    if (esVal(ex1, TipoNodo.INT) && esVal(ex2, TipoNodo.INT)) {
                        temp = new Hoja("" + (int) calcu(ex1, tipo, ex2), TipoNodo.INT);
                    } else {
                        temp = new Hoja("" + calcu(ex1, tipo, ex2), TipoNodo.DOUBLE);
                    }
                    break;
                }
        }
        return temp;
    }

    private boolean veriTD(Nodo ex1, TipoNodo tipo1, Nodo ex2, TipoNodo tipo2) {
        if ((ex1.getTipoNodo().equals(tipo1) && ex2.getTipoNodo().equals(tipo2))) {
            return true;
        } else {
            return false;
        }
    }

    private boolean verificarErrorSeman(Nodo ex1, TipoNodo tipo, Nodo ex2) {

        switch (tipo) {

            case MAS:
                if (ex1 == null || ex2 == null
                        || veriTD(ex1, TipoNodo.BOOLEAN, ex2, TipoNodo.BOOLEAN)
                        || veriTD(ex1, TipoNodo.BOOLEAN, ex2, TipoNodo.CHAR)
                        || veriTD(ex1, TipoNodo.CHAR, ex2, TipoNodo.BOOLEAN)) {
                    errorSemantico(ex1, tipo, ex2);
                    return true;
                } else {
                    return false;
                }
            case MENOS:
                if (ex1 == null || ex2 == null
                        || esVal(ex1, TipoNodo.STRING) || esVal(ex2, TipoNodo.STRING)
                        || veriTD(ex1, TipoNodo.BOOLEAN, ex2, TipoNodo.BOOLEAN)
                        || veriTD(ex1, TipoNodo.BOOLEAN, ex2, TipoNodo.CHAR)
                        || veriTD(ex1, TipoNodo.CHAR, ex2, TipoNodo.BOOLEAN)
                        || veriTD(ex1, TipoNodo.CHAR, ex2, TipoNodo.CHAR)) {
                    errorSemantico(ex1, tipo, ex2);
                    return true;
                } else {
                    return false;
                }
            case MULTI:
                if (ex1 == null || ex2 == null
                        || esVal(ex1, TipoNodo.STRING) || esVal(ex2, TipoNodo.STRING)
                        || esVal(ex1, TipoNodo.BOOLEAN) || esVal(ex2, TipoNodo.BOOLEAN)
                        || veriTD(ex1, TipoNodo.CHAR, ex2, TipoNodo.CHAR)) {
                    errorSemantico(ex1, tipo, ex2);
                    return true;
                } else {
                    return false;
                }
            case DIV:
                if (ex1 == null || ex2 == null
                        || esVal(ex1, TipoNodo.STRING) || esVal(ex2, TipoNodo.STRING)
                        || esVal(ex1, TipoNodo.BOOLEAN) || esVal(ex2, TipoNodo.BOOLEAN)
                        || veriTD(ex1, TipoNodo.CHAR, ex2, TipoNodo.CHAR)) {
                    errorSemantico(ex1, tipo, ex2);
                    return true;
                } else {
                    return false;
                }
            case POT:
                if (ex1 == null || ex2 == null
                        || esVal(ex1, TipoNodo.STRING) || esVal(ex2, TipoNodo.STRING)
                        || esVal(ex1, TipoNodo.BOOLEAN) || esVal(ex2, TipoNodo.BOOLEAN)
                        || esVal(ex1, TipoNodo.CHAR) || esVal(ex2, TipoNodo.CHAR)) {
                    errorSemantico(ex1, tipo, ex2);
                    return true;
                } else {
                    return false;
                }
            case MOD:
                if (ex1 == null || ex2 == null
                        || esVal(ex1, TipoNodo.STRING) || esVal(ex2, TipoNodo.STRING)
                        || esVal(ex1, TipoNodo.BOOLEAN) || esVal(ex2, TipoNodo.BOOLEAN)
                        || esVal(ex1, TipoNodo.CHAR) || esVal(ex2, TipoNodo.CHAR)) {
                    errorSemantico(ex1, tipo, ex2);
                    return true;
                } else {
                    return false;
                }
            case NEG:
                if (ex1 == null || esVal(ex1, TipoNodo.STRING) || esVal(ex1, TipoNodo.BOOLEAN)
                        || esVal(ex1, TipoNodo.CHAR)) {
                    errorSemantico(ex1, tipo, ex2);
                    return true;
                } else {
                    return false;
                }
        }

        return false;
    }

    private Nodo busTaSim(Nodo n) {
        Nodo temp = null;
        for (int i = 0; i < tSimbolos.size(); i++) {
            if (n.getValor().equals(tSimbolos.get(i).getId())) {
                temp = new Hoja(tSimbolos.get(i).getValor(), tSimbolos.get(i).getTipo());
                break;
            }
        }
        return temp;
    }

    private String concatenar(Nodo ex1, Nodo ex2) {
        if (ex1.getValor().equalsIgnoreCase("Expresión")) {
            convertidor(ex1);
        }
        if (ex1.getValor().equalsIgnoreCase("Expresión")) {
            convertidor(ex1);
        }
        return ex1.getValor() + ex2.getValor();
    }

    private double calcu(Nodo ex1, TipoNodo tipo, Nodo ex2) {
        double valor = 0;
        switch (tipo) {
            case MAS:
                valor = (Double.parseDouble(ex1.getValor()) + Double.parseDouble(ex2.getValor()));
                break;
            case MENOS:
                valor = (Double.parseDouble(ex1.getValor()) - Double.parseDouble(ex2.getValor()));
                break;
            case MULTI:
                valor = (Double.parseDouble(ex1.getValor()) * Double.parseDouble(ex2.getValor()));
                break;
            case DIV:
                valor = (Double.parseDouble(ex1.getValor()) / Double.parseDouble(ex2.getValor()));
                break;
            case POT:
                valor = Math.pow(Double.parseDouble(ex1.getValor()), Double.parseDouble(ex2.getValor()));
                break;
            case MOD:
                valor = (Double.parseDouble(ex1.getValor()) % Double.parseDouble(ex2.getValor()));
                break;
            case NEG:
                valor = (Double.parseDouble(ex1.getValor()) * -1);
                break;
        }
        return valor;
    }

    private boolean esVal(Nodo n, TipoNodo tipo) {//EVALUAR EN TABLA DE SIMBOLOS TAMBIEM
        return n.getTipoNodo().equals(tipo);
    }

    public Padre newIncr(String id, String sim, boolean idPrim, Nodo ex) {

        ArrayList<Nodo> temp = new ArrayList<>();
        if (ex != null) {
            temp.add(new Hoja(id, TipoNodo.IDENTIFICADOR));
            temp.add(ex);
        } else {
            if (idPrim) {
                temp.add(new Hoja(id, TipoNodo.IDENTIFICADOR));
                temp.add(new Hoja(sim, TipoNodo.INCR));
            } else {
                temp.add(new Hoja(sim, TipoNodo.INCR));
                temp.add(new Hoja(id, TipoNodo.IDENTIFICADOR));
            }
        }
        return new Padre("ACTUALIZACIÓN", TipoNodo.ACT, temp);
    }

    public Padre newTer(String td, ArrayList<Nodo> ids, Nodo c, Nodo ex1, Nodo ex2) {
        ArrayList<Nodo> temp = new ArrayList<>();
        if (td != null) {
            temp.add(new Hoja(td, TipoNodo.TIPODATO));
        }
        temp.addAll(ids);
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
        if (paramList != null) {
            temp.add(new Padre("Parámetros", TipoNodo.PAR, paramList));
        }
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
        temp.add(ex);
        if (ex instanceof Expresion) {
            texto.add(((Expresion) ex).getResult().getValor());//cambiar por obtener el valor real de la expresion
        } else {
            texto.add(((Hoja) ex).getValor());
        }
        return new Padre("WriteLine", TipoNodo.WrtLn, temp);
    }

    public Padre newMet(String id, ArrayList<Nodo> param, ArrayList<Nodo> sentencias) {
        ArrayList<Nodo> temp = new ArrayList<>();
        temp.add(new Hoja("void", TipoNodo.VOID));
        temp.add(new Hoja(id, TipoNodo.IDENTIFICADOR));
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
            temp.add(new Padre("Parámetros", TipoNodo.PAR, param));
        }
        if (sentencias != null) {
            temp.add(new Padre("Sentencias", TipoNodo.SENT, sentencias));
        }
        return new Padre("Metodo", TipoNodo.MTD, temp);
    }

    public Padre newIf(Nodo c, ArrayList<Nodo> sentencias, Nodo elif) {
        ArrayList<Nodo> temp = new ArrayList<>();
        temp.add(new Hoja("if", TipoNodo.IF));
        temp.add(c);
        temp.add(new Padre("Sentencias", TipoNodo.SENT, sentencias));
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
            temp.add(c);
        }
        temp.add(new Padre("Sentencias", TipoNodo.SENT, sentencias));
        if (elif != null) {
            temp.add(elif);
        }
        return new Padre("ELSE/ELSE_IF", TipoNodo.ELIF, temp);
    }

    public Padre newSwt(Nodo ex, Nodo c) {
        ArrayList<Nodo> temp = new ArrayList<>();
        temp.add(new Hoja("switch", TipoNodo.SWT));
        temp.add(ex);
        temp.add(c);
        return new Padre("SWITCH", TipoNodo.SWT, temp);
    }

    public Padre newCas(Nodo ex, ArrayList<Nodo> sentencias, Nodo c) {
        ArrayList<Nodo> temp = new ArrayList<>();
        if (ex != null) {
            temp.add(new Hoja("case", TipoNodo.CASE));
            temp.add(ex);
            temp.add(new Padre("Sentencias", TipoNodo.SENT, sentencias));
            if (c != null) {
                temp.add(c);
            }
            return new Padre("CASE", TipoNodo.CASE, temp);
        } else {
            temp.add(new Hoja("default", TipoNodo.BCR));
            temp.add(new Padre("Sentencias", TipoNodo.SENT, sentencias));

            return new Padre("DEFAULT", TipoNodo.DEF, temp);
        }
    }

    public Padre newWhil(Nodo c, ArrayList<Nodo> sentencias) {
        ArrayList<Nodo> temp = new ArrayList<>();
        temp.add(new Hoja("while", TipoNodo.WHILE));
        temp.add(c);
        temp.add(new Padre("Sentencias", TipoNodo.SENT, sentencias));
        return new Padre("WHILE", TipoNodo.WHILE, temp);
    }

    public Padre newDoWhil(Nodo c, ArrayList<Nodo> sentencias) {
        ArrayList<Nodo> temp = new ArrayList<>();
        temp.add(new Hoja("Do", TipoNodo.DO));
        temp.add(new Padre("Sentencias", TipoNodo.SENT, sentencias));

        temp.add(new Hoja("while", TipoNodo.WHILE));
        temp.add(c);
        return new Padre("DO WHILE", TipoNodo.DOWHILE, temp);
    }

    public Padre newDeas(String td, String id, Nodo ex) {
        ArrayList<Nodo> temp = new ArrayList<>();
        if (td != null) {
            temp.add(new Hoja(td, TipoNodo.TIPODATO));
        }
        temp.add(new Hoja(id, TipoNodo.IDENTIFICADOR));
        temp.add(ex);
        return new Padre("DEAS", TipoNodo.DEAS, temp);
    }

    public Padre newFor(Nodo d, Nodo c, Nodo cr, ArrayList<Nodo> sentencias) {
        ArrayList<Nodo> temp = new ArrayList<>();
        temp.add(new Hoja("for", TipoNodo.FOR));
        temp.add(d);
        temp.add(c);
        temp.add(cr);
        temp.add(new Padre("Sentencias", TipoNodo.SENT, sentencias));
        return new Padre("FOR", TipoNodo.FOR, temp);
    }

    public void errorSemantico(Nodo ex1, TipoNodo tipo, Nodo ex2) {

        if (ex1 == null && ex2 == null) {
            System.out.println("Error Semántico. Ambos valores son nulos");
            erroresSemanticos.add(new ErrorLSS(1, 1, "Semántico", "Error Semántico. Ambos valores son nulos"));
        } else if (ex2 != null) {
            System.out.println("Error Semántico. No se esperaba el valor: " + ex2.getValor() + ". Concatenado con un valor Nulo");
            erroresSemanticos.add(new ErrorLSS(1, 1, "Semántico", "Error Semántico. No se esperaba el valor: " + ex2.getValor() + ". Concatenado con un valor Nulo"));
        } else if (ex1 != null) {
            System.out.println("Error Semántico. No se esperaba el valor: " + ex1.getValor() + ". Concatenado con un valor Nulo, o con ese tipo de Dato");
            erroresSemanticos.add(new ErrorLSS(1, 1, "Semántico", "Error Semántico. No se esperaba el valor: " + ex1.getValor() + ".  Concatenado con un valor Nulo, o con ese tipo de Dato"));
        } else {
            System.out.println("Error Semántico. No se esperaba el valor: " + ex1.getValor() + " con: " + ex2.getValor());
            erroresSemanticos.add(new ErrorLSS(1, 1, "Semántico", "Error Semántico. No se esperaba el valor: " + ex1.getValor() + " con: " + ex2.getValor()));
        }
    }

    private void agTabSim(ArrayList<Nodo> arreglo, Nodo ex) {
        TipoNodo tipo = TipoNodo.SIMBOLO;
        String valor = "";
        if (ex != null) {
            if (ex instanceof Expresion) {
                if (((Expresion) ex).getResult() != null) {
                    tipo = ((Expresion) ex).getResult().getTipoNodo();
                    valor = ((Expresion) ex).getResult().getValor();
                }
            } else {
                tipo = ex.getTipoNodo();
                valor = ex.getValor();
            }
        }
        for (int i = 0; i < arreglo.size(); i++) {
            tSimbolos.add(new Simbolos(arreglo.get(i).getValor(), tipo, valor));
        }
    }

    public ArrayList<Simbolos> gettSimbolos() {
        return tSimbolos;
    }

    public void settSimbolos(ArrayList<Simbolos> tSimbolos) {
        this.tSimbolos = tSimbolos;
    }

    public ArrayList<ErrorLSS> getErroresSemanticos() {
        return erroresSemanticos;
    }

    public void setErroresSemanticos(ArrayList<ErrorLSS> erroresSemanticos) {
        this.erroresSemanticos = erroresSemanticos;
    }

    public ArrayList<String> getTexto() {
        return texto;
    }

    public void setTexto(ArrayList<String> texto) {
        this.texto = texto;
    }

}
