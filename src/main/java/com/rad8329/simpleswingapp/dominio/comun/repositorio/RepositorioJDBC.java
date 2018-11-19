package com.rad8329.simpleswingapp.dominio.comun.repositorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class RepositorioJDBC extends Repositorio {

    private final ConfiguracionJDBC configuracion;

    public RepositorioJDBC(ConfiguracionJDBC configuracion) {
        this.configuracion = configuracion;
    }

    public Connection conectar() {
        try {
            return DriverManager.getConnection(
                    configuracion.getUrl(),
                    configuracion.getUsuario(),
                    configuracion.getContrasena()
            );
        } catch (SQLException se) {
            Logger.getLogger(Repositorio.class.getName()).log(
                    Level.SEVERE,
                    "Ocurrió un error conectandose a la base de datos --> {0}",
                    se.getMessage()
            );
        }

        return null;
    }
}
