package main.java.uao.asiscontrol;

import main.java.uao.asiscontrol.negocio.gui.vistas.MainFrame;
import javax.swing.UIManager;
import main.java.uao.asiscontrol.negocio.gui.controles.ZonaControlador;
import main.java.uao.asiscontrol.negocio.repositorio.ZonaArchivoRepositorio;
import java.util.ArrayList;
import main.java.uao.asiscontrol.negocio.gui.controles.ControladorInterface;
import javax.swing.JPanel;

/**
 *
 * @author rad8329
 */
public class Aplicacion {

    private final ArrayList<ControladorInterface> controladores;

    public Aplicacion(ArrayList<ControladorInterface> controladores) {
        this.controladores = controladores;

        cargarTodoLosRegistros();
    }

    private void cargarTodoLosRegistros() {
        controladores.forEach((controlador) -> {
            controlador.cargarTodoLosRegistros();
        });
    }

    public ArrayList<JPanel> getPaneles() {
        ArrayList<JPanel> paneles = new ArrayList();

        controladores.forEach((controlador) -> {
            paneles.add(controlador.construirPanel());
        });

        return paneles;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException
                | InstantiationException
                | IllegalAccessException
                | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Aplicacion.class.getName()).log(java.util.logging.Level.SEVERE, ex.getMessage());
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
