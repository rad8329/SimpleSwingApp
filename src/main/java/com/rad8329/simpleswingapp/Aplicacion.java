package com.rad8329.simpleswingapp;

import com.rad8329.simpleswingapp.gui.evento.EventoControlador;
import com.rad8329.simpleswingapp.gui.evento.ProgramacionControlador;
import com.rad8329.simpleswingapp.gui.reporte.ReporteControlador;
import com.rad8329.simpleswingapp.gui.zona.ZonaControlador;
import com.rad8329.simpleswingapp.gui.MainFrame;
import com.rad8329.simpleswingapp.dominio.comun.repositorio.ConfiguracionJDBC;
import com.rad8329.simpleswingapp.infraestructura.dependencia.DependenciaMysqlRepositorio;
import com.rad8329.simpleswingapp.infraestructura.evento.EventoMysqlRepositorio;
import com.rad8329.simpleswingapp.infraestructura.persona.PersonaMysqlRepositorio;
import com.rad8329.simpleswingapp.infraestructura.evento.ProgramacionMysqlRepositorio;
import com.rad8329.simpleswingapp.infraestructura.programa.ProgramaMysqlRepositorio;
import com.rad8329.simpleswingapp.infraestructura.reporte.ReporteBaseMysqlRepositorio;
import com.rad8329.simpleswingapp.dominio.comun.repositorio.Repositorio;
import com.rad8329.simpleswingapp.infraestructura.zona.ZonaMysqlRepositorio;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.UIManager;
import com.rad8329.simpleswingapp.gui.comun.controlador.ControladorPanelInterface;

public final class Aplicacion {

    private static Aplicacion aplicacion;
    private HashMap<String, ControladorPanelInterface> controladores;
    private HashMap<String, Repositorio> repositorios;

    public List<JPanel> getPaneles() {
        List<JPanel> paneles = new ArrayList<>();

        controladores.forEach((nombre, controlador) -> {
            if (controlador.esLanzable()) {
                paneles.add(controlador.renderizar());
            }
        });

        return paneles;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException
                | InstantiationException
                | IllegalAccessException
                | javax.swing.UnsupportedLookAndFeelException ce) {
            Logger.getLogger(Aplicacion.class.getName()).log(
                    Level.SEVERE,
                    ce.getMessage()
            );
        }

        java.awt.EventQueue.invokeLater(() -> {
            iniciar();

            new MainFrame().setVisible(true);
        });
    }

    public static Aplicacion get() {
        return aplicacion;
    }

    public static Repositorio repositorio(String nombre) {
        return aplicacion.repositorios.get(nombre);
    }

    public static ControladorPanelInterface controlador(String nombre) {
        return aplicacion.controladores.get(nombre);
    }

    private static void iniciar() {
        if (aplicacion == null) {
            aplicacion = new Aplicacion();

            ConfiguracionJDBC configuracionDB = prepararConfiguracionDB();

            aplicacion.controladores = new LinkedHashMap<>();
            aplicacion.repositorios = new LinkedHashMap<>();

            aplicacion.repositorios.put("zonas", new ZonaMysqlRepositorio(configuracionDB));
            aplicacion.repositorios.put("eventos", new EventoMysqlRepositorio(configuracionDB));
            aplicacion.repositorios.put("programas", new ProgramaMysqlRepositorio(configuracionDB));
            aplicacion.repositorios.put("dependencias", new DependenciaMysqlRepositorio(configuracionDB));
            aplicacion.repositorios.put("programaciones", new ProgramacionMysqlRepositorio(configuracionDB));
            aplicacion.repositorios.put("personas", new PersonaMysqlRepositorio(configuracionDB));
            aplicacion.repositorios.put("reportes", new ReporteBaseMysqlRepositorio(configuracionDB));

            aplicacion.controladores.put("zonas", new ZonaControlador("Zonas"));
            aplicacion.controladores.put("eventos", new EventoControlador("Eventos"));            
            aplicacion.controladores.put("programaciones", new ProgramacionControlador("Programaciones"));
            aplicacion.controladores.put("reportes", new ReporteControlador("Reportes"));
        }
    }

    private static ConfiguracionJDBC prepararConfiguracionDB() {     
        try {
            InputStream inputStream;

            inputStream = Aplicacion.class.getResourceAsStream("/config/config.properties");
           
            Properties propiedades = new Properties();
            propiedades.load(inputStream);
 
            return new ConfiguracionJDBC(
                    propiedades.getProperty("mysql_user"),
                    propiedades.getProperty("mysql_password"),
                    propiedades.getProperty("mysql_url"));

        } catch (IOException se) {
            Logger.getLogger(Aplicacion.class.getName()).log(
                    Level.SEVERE,
                    "Ocurrió un error leyendo el archivo de configuraciones --> {0}",
                    se.getMessage()
            );
        }

        return null;
    }
}
