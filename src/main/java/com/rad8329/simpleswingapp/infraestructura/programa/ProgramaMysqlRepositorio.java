package com.rad8329.simpleswingapp.infraestructura.programa;

import com.rad8329.simpleswingapp.dominio.comun.repositorio.ConfiguracionJDBC;
import com.rad8329.simpleswingapp.dominio.comun.repositorio.RepositorioCrudInterface;
import com.rad8329.simpleswingapp.dominio.comun.repositorio.RepositorioJDBC;
import com.rad8329.simpleswingapp.dominio.programa.Programa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProgramaMysqlRepositorio extends RepositorioJDBC implements RepositorioCrudInterface<Programa> {

    public ProgramaMysqlRepositorio(ConfiguracionJDBC configuracion) {
        super(configuracion);
    }

    @Override
    public boolean insertar(Programa modelo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean actualizar(Programa anterior, Programa nuevo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean eliminar(Programa modelo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Programa> consultarTodo() {
        Connection conexion = conectar();

        List<Programa> dependencias = new ArrayList<>();

        if (conexion != null) {
            String sql = "SELECT * FROM programasAcademicos";

            try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
                ResultSet resultado = preparedStatement.executeQuery();

                Logger.getLogger(ProgramaMysqlRepositorio.class.getName()).log(
                        Level.INFO,
                        "Se ejecutó la consulta --> {0}",
                        preparedStatement.toString()
                );

                while (resultado.next()) {

                    dependencias.add(new Programa(resultado.getInt("codigo"), resultado.getString("nombre")));
                }

                conexion.close();
            } catch (SQLException se) {
                Logger.getLogger(ProgramaMysqlRepositorio.class.getName()).log(
                        Level.SEVERE,
                        "Ocurrió un error ejecutando la consulta --> {0}",
                        se.getMessage()
                );

                try {
                    conexion.close();
                } catch (SQLException sec) {
                    Logger.getLogger(ProgramaMysqlRepositorio.class.getName()).log(
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
