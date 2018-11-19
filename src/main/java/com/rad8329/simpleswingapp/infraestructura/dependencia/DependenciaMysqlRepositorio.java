package com.rad8329.simpleswingapp.infraestructura.dependencia;

import com.rad8329.simpleswingapp.dominio.comun.repositorio.ConfiguracionJDBC;
import com.rad8329.simpleswingapp.dominio.comun.repositorio.RepositorioCrudInterface;
import com.rad8329.simpleswingapp.dominio.comun.repositorio.RepositorioJDBC;
import com.rad8329.simpleswingapp.dominio.dependencia.Dependencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DependenciaMysqlRepositorio extends RepositorioJDBC implements RepositorioCrudInterface<Dependencia> {

    public DependenciaMysqlRepositorio(ConfiguracionJDBC configuracion) {
        super(configuracion);
    }

    @Override
    public boolean insertar(Dependencia modelo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean actualizar(Dependencia anterior, Dependencia nuevo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean eliminar(Dependencia modelo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Dependencia> consultarTodo() {
        Connection conexion = conectar();

        List<Dependencia> dependencias = new ArrayList<>();

        if (conexion != null) {
            String sql = "SELECT * FROM dependencias";

            try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
                ResultSet resultado = preparedStatement.executeQuery();

                Logger.getLogger(DependenciaMysqlRepositorio.class.getName()).log(
                        Level.INFO,
                        "Se ejecutó la consulta --> {0}",
                        preparedStatement.toString()
                );

                while (resultado.next()) {

                    dependencias.add(new Dependencia(resultado.getInt("codigo"), resultado.getString("nombre")));
                }

                conexion.close();
            } catch (SQLException se) {
                Logger.getLogger(DependenciaMysqlRepositorio.class.getName()).log(
                        Level.SEVERE,
                        "Ocurrió un error ejecutando la consulta --> {0}",
                        se.getMessage()
                );

                try {
                    conexion.close();
                } catch (SQLException sec) {
                    Logger.getLogger(DependenciaMysqlRepositorio.class.getName()).log(
                            Level.SEVERE,
                            "Ocurrió un error cerrando la conexión --> {0}",
                            sec.getMessage()
                    );
                }
            }
        }

        return dependencias;
    }
}
