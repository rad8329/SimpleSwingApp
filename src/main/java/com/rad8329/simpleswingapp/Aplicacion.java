package com.rad8329.simpleswingapp;

import com.rad8329.simpleswingapp.negocio.gui.controles.ControladorInterface;
import com.rad8329.simpleswingapp.negocio.gui.controles.ZonaControlador;
import com.rad8329.simpleswingapp.negocio.gui.vistas.MainFrame;
import com.rad8329.simpleswingapp.negocio.repositorio.ZonaArchivoRepositorio;

import javax.swing.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author rad8329
 */
public class Aplicacion {

    private final ArrayList<ControladorInterface> controladores;

    private Aplicacion(ArrayList<ControladorInterface> controladores) {
        this.controladores = controladores;

        cargarTodoLosRegistros();
    }

    private void cargarTodoLosRegistros() {
        controladores.forEach((controlador) -> controlador.cargarTodoLosRegistros());
    }

    public ArrayList<JPanel> getPaneles() {
        ArrayList<JPanel> paneles = new ArrayList<>();

        controladores.forEach((controlador) -> {
            paneles.add(controlador.construirPanel());
        });

        return paneles;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException
                | InstantiationException
                | IllegalAccessException
                | javax.swing.UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Aplicacion.class.getName()).log(
                    Level.SEVERE,
                    ex.getMessage()
            );
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            ArrayList<ControladorInterface> controles = new ArrayList<>();

            controles.add(new ZonaControlador(
                    "Zonas",
                    new ZonaArchivoRepositorio("zonas")
            ));

            controles.add(new ZonaControlador(
                    "Regiones",
                    new ZonaArchivoRepositorio("regiones")
            ));

            Aplicacion aplicacion = new Aplicacion(controles);

            new MainFrame(aplicacion).setVisible(true);
        });
    }
}
