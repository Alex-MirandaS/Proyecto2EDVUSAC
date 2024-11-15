/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Proyecto.Principal;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author Alex
 */
public class PrincipalGUI extends javax.swing.JFrame {

    private Principal principal;

    public PrincipalGUI(Principal principal) {
        initComponents();
//        guardar.setEnabled(false);
//        reportes.setEnabled(false);
//        busqueda.setEnabled(false);
        this.principal = principal;
        this.areaConsola.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPasswordField1 = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();
        nuevo = new javax.swing.JButton();
        abrir = new javax.swing.JButton();
        reportes = new javax.swing.JButton();
        guardar = new javax.swing.JButton();
        guardarComo = new javax.swing.JButton();
        salir = new javax.swing.JButton();
        acercaDe = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        atras = new javax.swing.JButton();
        adelante = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        ejecutar = new javax.swing.JButton();
        tablaSimbolos = new javax.swing.JButton();
        tablaErrores = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaEditor = new javax.swing.JTextArea();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        areaConsola = new javax.swing.JTextArea();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        jPasswordField1.setText("jPasswordField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("INTERPRETE_U");
        setBackground(new java.awt.Color(0, 0, 0));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new java.awt.GridLayout(9, 1));

        nuevo.setBackground(new java.awt.Color(0, 0, 0));
        nuevo.setFont(new java.awt.Font("Courier 10 Pitch", 1, 14)); // NOI18N
        nuevo.setForeground(new java.awt.Color(255, 255, 255));
        nuevo.setText("NUEVO");
        nuevo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nuevoMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                nuevoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                nuevoMouseReleased(evt);
            }
        });
        nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoActionPerformed(evt);
            }
        });
        jPanel1.add(nuevo);

        abrir.setBackground(new java.awt.Color(0, 0, 0));
        abrir.setFont(new java.awt.Font("Courier 10 Pitch", 1, 14)); // NOI18N
        abrir.setForeground(new java.awt.Color(255, 255, 255));
        abrir.setText("ABRIR");
        abrir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                abrirMousePressed(evt);
            }
        });
        abrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirActionPerformed(evt);
            }
        });
        jPanel1.add(abrir);

        reportes.setBackground(new java.awt.Color(0, 0, 0));
        reportes.setFont(new java.awt.Font("Courier 10 Pitch", 1, 14)); // NOI18N
        reportes.setForeground(new java.awt.Color(255, 255, 255));
        reportes.setText("REPORTES");
        reportes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                reportesMousePressed(evt);
            }
        });
        reportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportesActionPerformed(evt);
            }
        });
        jPanel1.add(reportes);

        guardar.setBackground(new java.awt.Color(0, 0, 0));
        guardar.setFont(new java.awt.Font("Courier 10 Pitch", 1, 14)); // NOI18N
        guardar.setForeground(new java.awt.Color(255, 255, 255));
        guardar.setText("GUARDAR");
        guardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                guardarMousePressed(evt);
            }
        });
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });
        jPanel1.add(guardar);

        guardarComo.setBackground(new java.awt.Color(0, 0, 0));
        guardarComo.setFont(new java.awt.Font("Courier 10 Pitch", 1, 14)); // NOI18N
        guardarComo.setForeground(new java.awt.Color(255, 255, 255));
        guardarComo.setText("GUARDAR COMO");
        guardarComo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                guardarComoMousePressed(evt);
            }
        });
        guardarComo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarComoActionPerformed(evt);
            }
        });
        jPanel1.add(guardarComo);

        salir.setBackground(new java.awt.Color(0, 0, 0));
        salir.setFont(new java.awt.Font("Courier 10 Pitch", 1, 14)); // NOI18N
        salir.setForeground(new java.awt.Color(255, 255, 255));
        salir.setText("SALIR");
        salir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                salirMousePressed(evt);
            }
        });
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });
        jPanel1.add(salir);

        acercaDe.setBackground(new java.awt.Color(0, 0, 0));
        acercaDe.setFont(new java.awt.Font("Courier 10 Pitch", 1, 14)); // NOI18N
        acercaDe.setForeground(new java.awt.Color(255, 255, 255));
        acercaDe.setText("ACERCA DE");
        acercaDe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                acercaDeMousePressed(evt);
            }
        });
        acercaDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acercaDeActionPerformed(evt);
            }
        });
        jPanel1.add(acercaDe);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setLayout(new java.awt.GridLayout(1, 2));

        atras.setBackground(new java.awt.Color(0, 0, 0));
        atras.setFont(new java.awt.Font("Courier 10 Pitch", 1, 14)); // NOI18N
        atras.setForeground(new java.awt.Color(255, 255, 255));
        atras.setText("<-");
        atras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                atrasMousePressed(evt);
            }
        });
        atras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atrasActionPerformed(evt);
            }
        });
        jPanel2.add(atras);

        adelante.setBackground(new java.awt.Color(0, 0, 0));
        adelante.setFont(new java.awt.Font("Courier 10 Pitch", 1, 14)); // NOI18N
        adelante.setForeground(new java.awt.Color(255, 255, 255));
        adelante.setText("->");
        adelante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                adelanteMousePressed(evt);
            }
        });
        jPanel2.add(adelante);

        jPanel1.add(jPanel2);

        getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_START);

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setLayout(new java.awt.GridLayout(1, 1));

        jPanel15.setLayout(new java.awt.GridLayout(1, 5));

        ejecutar.setBackground(new java.awt.Color(0, 0, 0));
        ejecutar.setFont(new java.awt.Font("Courier 10 Pitch", 1, 14)); // NOI18N
        ejecutar.setForeground(new java.awt.Color(255, 255, 255));
        ejecutar.setText("Ejecutar");
        ejecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ejecutarActionPerformed(evt);
            }
        });
        jPanel15.add(ejecutar);

        tablaSimbolos.setBackground(new java.awt.Color(0, 0, 0));
        tablaSimbolos.setFont(new java.awt.Font("Courier 10 Pitch", 1, 14)); // NOI18N
        tablaSimbolos.setForeground(new java.awt.Color(255, 255, 255));
        tablaSimbolos.setText("Tabla de Simbolos");
        tablaSimbolos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tablaSimbolosActionPerformed(evt);
            }
        });
        jPanel15.add(tablaSimbolos);

        tablaErrores.setBackground(new java.awt.Color(0, 0, 0));
        tablaErrores.setFont(new java.awt.Font("Courier 10 Pitch", 1, 14)); // NOI18N
        tablaErrores.setForeground(new java.awt.Color(255, 255, 255));
        tablaErrores.setText("Errores");
        tablaErrores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tablaErroresActionPerformed(evt);
            }
        });
        jPanel15.add(tablaErrores);

        jPanel3.add(jPanel15);

        getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_END);

        jPanel4.setLayout(new java.awt.GridLayout(1, 2));

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));
        jPanel5.setLayout(new java.awt.BorderLayout());

        areaEditor.setBackground(new java.awt.Color(102, 102, 102));
        areaEditor.setColumns(20);
        areaEditor.setRows(5);
        jScrollPane1.setViewportView(areaEditor);

        jPanel5.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel7.setLayout(new java.awt.GridLayout(1, 2));

        jPanel8.setBackground(new java.awt.Color(0, 0, 0));
        jPanel8.setForeground(new java.awt.Color(255, 255, 255));
        jPanel8.setFont(new java.awt.Font("Courier 10 Pitch", 1, 14)); // NOI18N

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Courier 10 Pitch", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Editor");
        jPanel8.add(jLabel3);

        jPanel7.add(jPanel8);

        jPanel5.add(jPanel7, java.awt.BorderLayout.PAGE_END);

        jPanel4.add(jPanel5);

        jPanel6.setBackground(new java.awt.Color(0, 0, 0));
        jPanel6.setLayout(new java.awt.GridLayout(1, 1));

        jPanel9.setLayout(new java.awt.BorderLayout());

        areaConsola.setBackground(new java.awt.Color(102, 102, 102));
        areaConsola.setColumns(20);
        areaConsola.setRows(5);
        jScrollPane2.setViewportView(areaConsola);

        jPanel9.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel10.setLayout(new java.awt.GridLayout(1, 2));

        jPanel11.setBackground(new java.awt.Color(0, 0, 0));
        jPanel11.setFont(new java.awt.Font("Courier 10 Pitch", 1, 14)); // NOI18N

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Courier 10 Pitch", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Consola");
        jPanel11.add(jLabel1);

        jPanel10.add(jPanel11);

        jPanel9.add(jPanel10, java.awt.BorderLayout.PAGE_END);

        jPanel6.add(jPanel9);

        jPanel4.add(jPanel6);

        getContentPane().add(jPanel4, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void abrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirActionPerformed
        principal.cargarArchivo();
//        guardar.setEnabled(false);
//        reportes.setEnabled(true);
//        busqueda.setEnabled(true);
    }//GEN-LAST:event_abrirActionPerformed

    private void reportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportesActionPerformed
        //principal.reportes();
    }//GEN-LAST:event_reportesActionPerformed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        principal.guardarArchivo();
//        guardar.setEnabled(false);
//        reportes.setEnabled(false);
//        busqueda.setEnabled(false);
//        abrir.setEnabled(true);
    }//GEN-LAST:event_guardarActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_salirActionPerformed

    private void guardarComoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarComoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_guardarComoActionPerformed

    private void nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoActionPerformed
        principal.nuevoArchivo();
//        reportes.setEnabled(false);
//        busqueda.setEnabled(false);
//        guardar.setEnabled(false);
    }//GEN-LAST:event_nuevoActionPerformed

    private void nuevoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nuevoMousePressed

    }//GEN-LAST:event_nuevoMousePressed

    private void abrirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_abrirMousePressed

    }//GEN-LAST:event_abrirMousePressed

    private void reportesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportesMousePressed

    }//GEN-LAST:event_reportesMousePressed

    private void guardarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guardarMousePressed

    }//GEN-LAST:event_guardarMousePressed

    private void guardarComoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guardarComoMousePressed

    }//GEN-LAST:event_guardarComoMousePressed

    private void salirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salirMousePressed

    }//GEN-LAST:event_salirMousePressed

    private void atrasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_atrasMousePressed

    }//GEN-LAST:event_atrasMousePressed

    private void adelanteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adelanteMousePressed

    }//GEN-LAST:event_adelanteMousePressed

    private void atrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atrasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_atrasActionPerformed

    private void nuevoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nuevoMouseClicked

    }//GEN-LAST:event_nuevoMouseClicked

    private void nuevoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nuevoMouseReleased

    }//GEN-LAST:event_nuevoMouseReleased

    private void acercaDeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_acercaDeMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_acercaDeMousePressed

    private void acercaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acercaDeActionPerformed
        //JOptionPane.showMessageDialog(null, principal.getDatosCreador());
    }//GEN-LAST:event_acercaDeActionPerformed

    private void ejecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ejecutarActionPerformed
        principal.traducir();
    }//GEN-LAST:event_ejecutarActionPerformed

    private void tablaErroresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tablaErroresActionPerformed
        principal.verificarErrores();
    }//GEN-LAST:event_tablaErroresActionPerformed

    private void tablaSimbolosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tablaSimbolosActionPerformed
        principal.verificarTSimbolos();
    }//GEN-LAST:event_tablaSimbolosActionPerformed

    public JTextArea getAreaConsola() {
        return areaConsola;
    }

    public void setAreaConsola(JTextArea areaConsola) {
        this.areaConsola = areaConsola;
    }

    public JTextArea getAreaEditor() {
        return areaEditor;
    }

    public void setAreaEditor(JTextArea areaEditor) {
        this.areaEditor = areaEditor;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton abrir;
    private javax.swing.JButton acercaDe;
    private javax.swing.JButton adelante;
    private javax.swing.JTextArea areaConsola;
    private javax.swing.JTextArea areaEditor;
    private javax.swing.JButton atras;
    private javax.swing.JButton ejecutar;
    private javax.swing.JButton guardar;
    private javax.swing.JButton guardarComo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton nuevo;
    private javax.swing.JButton reportes;
    private javax.swing.JButton salir;
    private javax.swing.JButton tablaErrores;
    private javax.swing.JButton tablaSimbolos;
    // End of variables declaration//GEN-END:variables
}
