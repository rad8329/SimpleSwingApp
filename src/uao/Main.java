package uao;

import uao.vistas.MainFrame;
import javax.swing.UIManager;
import uao.controles.ZonaControlador;

/**
 *
 * @author rad8329
 */
public class Main {
    
    public static ZonaControlador zonaControlador = new ZonaControlador();  

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
                Main.cargar();
            }
        });
    }
    
    public static void cargar() {
        zonaControlador.cargarTodo();
    }
}
