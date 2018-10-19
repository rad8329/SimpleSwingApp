package com.rad8329.simpleswingapp;

import com.rad8329.simpleswingapp.negocio.gui.controles.LanzadorInterface;
import com.rad8329.simpleswingapp.negocio.gui.controles.ZonaControlador;
import com.rad8329.simpleswingapp.negocio.gui.vistas.MainFrame;
import com.rad8329.simpleswingapp.negocio.repositorio.ZonaArchivoRepositorio;

import javax.swing.JPanel;
import javax.swing.UIManager;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author rad8329
 */
public class Aplicacion {

    private final ArrayList<LanzadorInterface> controladores;

    private Aplicacion(ArrayList<LanzadorInterface> controladores) {
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
        try {
            //Metal theme
            UIManager.setLookAndFeel(
                    UIManager.getCrossPlatformLookAndFeelClassName()
            );
        } catch (ClassNotFoundException
                | InstantiationException
                | IllegalAccessException
                | javax.swing.UnsupportedLookAndFeelException ex) {
            
            Logger.getLogger(Aplicacion.class.getName()).log(
                    Level.SEVERE,
                    ex.getMessage()
            );
        }

        java.awt.EventQueue.invokeLater(() -> {
            ArrayList<LanzadorInterface> controles = new ArrayList<>();

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
