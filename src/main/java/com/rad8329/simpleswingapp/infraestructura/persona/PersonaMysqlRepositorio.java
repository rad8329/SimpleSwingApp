package com.rad8329.simpleswingapp.infraestructura.persona;

import com.rad8329.simpleswingapp.dominio.comun.repositorio.ConfiguracionJDBC;
import com.rad8329.simpleswingapp.dominio.comun.repositorio.RepositorioCrudInterface;
import com.rad8329.simpleswingapp.dominio.comun.repositorio.RepositorioJDBC;
import com.rad8329.simpleswingapp.dominio.persona.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonaMysqlRepositorio extends RepositorioJDBC implements RepositorioCrudInterface<Persona> {

    public PersonaMysqlRepositorio(ConfiguracionJDBC configuracion) {
        super(configuracion);
    }

    @Override
    public List<Persona> consultarTodo() {
        Connection conexion = conectar();

        List<Persona> personas = new ArrayList<>();

        if (conexion != null) {
            String sql = "SELECT * FROM personas";

            try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
                ResultSet resultado = preparedStatement.executeQuery();

                Logger.getLogger(PersonaMysqlRepositorio.class.getName()).log(
                        Level.INFO,
                        "Se ejecutó la consulta --> {0}",
                        preparedStatement.toString()
                );

                while (resultado.next()) {

                    personas.add(
                            new Persona(
                                    resultado.getInt("codigo"),
                                    resultado.getString("nombres"),
                                    resultado.getString("apellidos"),
                                    resultado.getInt("codigo_rol")
                            )
                    );
                }

                conexion.close();
            } catch (SQLException se) {
                Logger.getLogger(PersonaMysqlRepositorio.class.getName()).log(
                        Level.SEVERE,
                        "Ocurrió un error ejecutando la consulta --> {0}",
                        se.getMessage()
                );

                try {
                    conexion.close();
                } catch (SQLException sec) {
                    Logger.getLogger(PersonaMysqlRepositorio.class.getName()).log(
                            Level.SEVERE,
                            "Ocurrió un error cerrando la conexión --> {0}",
                            sec.getMessage()
                    );
                }
            }
        }

        return personas;
    }

    @Override
    public boolean insertar(Persona modelo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean actualizar(Persona anterior, Persona nuevo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean eliminar(Persona modelo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
