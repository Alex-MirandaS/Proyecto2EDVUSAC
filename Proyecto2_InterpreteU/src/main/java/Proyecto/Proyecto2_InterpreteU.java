/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package Proyecto;

import java.util.ArrayList;

/**
 *
 * @author alex
 */
public class Proyecto2_InterpreteU {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        ArrayList<String> array = new ArrayList<>();
        array.add("HOLA");
        array.add("ADIOS");
        array.add(0,"esto va de primero");
        
        for (int i = 0; i < array.size(); i++) {
            System.out.println(array.get(i));
        }
        System.out.println(array.get(array.size()-1));
    }
}
