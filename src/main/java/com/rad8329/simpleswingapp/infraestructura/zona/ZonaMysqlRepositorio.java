package com.rad8329.simpleswingapp.infraestructura.zona;

import com.rad8329.simpleswingapp.dominio.comun.repositorio.ConfiguracionJDBC;
import com.rad8329.simpleswingapp.dominio.comun.repositorio.RepositorioCrudInterface;
import com.rad8329.simpleswingapp.dominio.comun.repositorio.RepositorioJDBC;
import com.rad8329.simpleswingapp.dominio.zona.Zona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ZonaMysqlRepositorio extends RepositorioJDBC implements RepositorioCrudInterface<Zona> {

    public ZonaMysqlRepositorio(ConfiguracionJDBC configuracion) {
        super(configuracion);
    }

    @Override
    public boolean insertar(Zona modelo) {
        Connection conexion = conectar();

        if (conexion != null) {
            String sql = "INSERT INTO zonas VALUES(?,?,?,?,?)";

            try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {

                preparedStatement.setInt(1, modelo.getCodigo_zona());
                preparedStatement.setString(2, modelo.getNombre());
                preparedStatement.setString(3, modelo.getDescripcion());
                preparedStatement.setBoolean(4, modelo.tieneControl_de_acceso());

                if (modelo.getDireccion_ip_cerradura().equals("")) {
                    preparedStatement.setNull(5, java.sql.Types.VARCHAR);
                } else {
                    preparedStatement.setString(5, modelo.getDireccion_ip_cerradura());

                }

                boolean resultado = preparedStatement.executeUpdate() > 0; // Se ejecuta la consulta

                Logger.getLogger(ZonaMysqlRepositorio.class.getName()).log(
                        Level.INFO,
                        "Se ejecutó la consulta --> {0}",
                        preparedStatement.toString()
                );

                preparedStatement.close();
                conexion.close();

                return resultado;

            } catch (SQLException se) {

                Logger.getLogger(ZonaMysqlRepositorio.class.getName()).log(
                        Level.SEVERE,
                        "Ocurrió un error ejecutando la consulta --> {0}",
                        se.getMessage()
                );
            }
        }

        return false;
    }

    @Override
    public boolean actualizar(Zona anterior, Zona nuevo) {
        Connection conexion = conectar();

        if (conexion != null) {
            String sql = "UPDATE zonas SET codigo_zona = ?, nombre = ?, "
                    + "descripcion = ?, control_acceso = ?, ip_cerradura = ? "
                    + "WHERE codigo_zona = ?";

            try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {

                preparedStatement.setInt(1, nuevo.getCodigo_zona());
                preparedStatement.setString(2, nuevo.getNombre());
                preparedStatement.setString(3, nuevo.getDescripcion());
                preparedStatement.setBoolean(4, nuevo.tieneControl_de_acceso());

                if (nuevo.getDireccion_ip_cerradura().equals("")) {
                    preparedStatement.setNull(5, java.sql.Types.VARCHAR);
                } else {
                    preparedStatement.setString(5, nuevo.getDireccion_ip_cerradura());

                }

                preparedStatement.setInt(6, anterior.getCodigo_zona());

                boolean resultado = preparedStatement.executeUpdate() > 0; // Se ejecuta la consulta

                Logger.getLogger(ZonaMysqlRepositorio.class.getName()).log(
                        Level.INFO,
                        "Se ejecutó la consulta --> {0}",
                        preparedStatement.toString()
                );

                preparedStatement.close();
                conexion.close();

                return resultado;

            } catch (SQLException se) {

                Logger.getLogger(ZonaMysqlRepositorio.class.getName()).log(
                        Level.SEVERE,
                        "Ocurrió un error ejecutando la consulta --> {0}",
                        se.getMessage()
                );
            }
        }

        return false;
    }

    @Override
    public boolean eliminar(Zona modelo) {
        Connection conexion = conectar();

        if (conexion != null) {
            String sql = "DELETE FROM zonas WHERE codigo_zona = ?";

            try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {

                preparedStatement.setInt(1, modelo.getCodigo_zona());

                boolean resultado = preparedStatement.executeUpdate() > 0; // Se ejecuta la consulta

                Logger.getLogger(ZonaMysqlRepositorio.class.getName()).log(
                        Level.INFO,
                        "Se ejecutó la consulta --> {0}",
                        preparedStatement.toString()
                );

                preparedStatement.close();
                conexion.close();

                return resultado;

            } catch (SQLException se) {

                Logger.getLogger(ZonaMysqlRepositorio.class.getName()).log(
                        Level.SEVERE,
                        "Ocurrió un error ejecutando la consulta --> {0}",
                        se.getMessage()
                );
            }
        }

        return false;
    }

    @Override
    public List<Zona> consultarTodo() {
        Connection conexion = conectar();

        List<Zona> zonas = new ArrayList<>();

        if (conexion != null) {
            String sql = "SELECT * FROM zonas";

            try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
                ResultSet resultado = preparedStatement.executeQuery();

                Logger.getLogger(ZonaMysqlRepositorio.class.getName()).log(
                        Level.INFO,
                        "Se ejecutó la consulta --> {0}",
                        preparedStatement.toString()
                );

                while (resultado.next()) {

                    zonas.add(
                            new Zona(
                                    resultado.getInt("codigo_zona"),
                                    resultado.getString("nombre"),
                                    resultado.getString("descripcion"),
                                    resultado.getInt("control_acceso") != 0,
                                    resultado.getString("ip_cerradura")
                            )
                    );
                }

                conexion.close();
            } catch (SQLException se) {
                Logger.getLogger(ZonaMysqlRepositorio.class.getName()).log(
                        Level.SEVERE,
                        "Ocurrió un error ejecutando la consulta --> {0}",
                        se.getMessage()
                );

                try {
                    conexion.close();
                } catch (SQLException sec) {
                    Logger.getLogger(ZonaMysqlRepositorio.class.getName()).log(
                            Level.SEVERE,
                            "Ocurrió un error cerrando la conexión --> {0}",
                            sec.getMessage()
                    );
                }
            }
        }

        return zonas;
    }
}
