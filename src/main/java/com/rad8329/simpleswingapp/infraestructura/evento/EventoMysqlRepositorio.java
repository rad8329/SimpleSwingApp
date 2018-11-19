package com.rad8329.simpleswingapp.infraestructura.evento;

import com.rad8329.simpleswingapp.dominio.comun.repositorio.ConfiguracionJDBC;
import com.rad8329.simpleswingapp.dominio.comun.repositorio.RepositorioCrudInterface;
import com.rad8329.simpleswingapp.dominio.comun.repositorio.RepositorioJDBC;
import com.rad8329.simpleswingapp.dominio.dependencia.Dependencia;
import com.rad8329.simpleswingapp.dominio.evento.Evento;
import com.rad8329.simpleswingapp.dominio.programa.Programa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EventoMysqlRepositorio extends RepositorioJDBC implements RepositorioCrudInterface<Evento> {

    public EventoMysqlRepositorio(ConfiguracionJDBC configuracion) {
        super(configuracion);
    }

    @Override
    public boolean insertar(Evento modelo) {
        Connection conexion = conectar();

        if (conexion != null) {
            String sql = "INSERT INTO eventos (nombre, descripcion, codigo_programa, codigo_dependencia) VALUES(?,?,?,?)";

            try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {

                preparedStatement.setString(1, modelo.getNombre());
                preparedStatement.setString(2, modelo.getDescripcion());
                
                if (modelo.getCodigo_programa() == 0) {
                    preparedStatement.setNull(3, java.sql.Types.INTEGER);
                } else {
                    preparedStatement.setInt(3, modelo.getCodigo_programa());
                }

                if (modelo.getCodigo_dependencia() == 0) {
                    preparedStatement.setNull(4, java.sql.Types.INTEGER);
                } else {
                    preparedStatement.setInt(4, modelo.getCodigo_dependencia());
                }

                boolean resultado = preparedStatement.executeUpdate() > 0; // Se ejecuta la consulta

                Logger.getLogger(EventoMysqlRepositorio.class.getName()).log(
                        Level.INFO,
                        "Se ejecutó la consulta --> {0}",
                        preparedStatement.toString()
                );

                preparedStatement.close();
                conexion.close();

                return resultado;

            } catch (SQLException se) {

                Logger.getLogger(EventoMysqlRepositorio.class.getName()).log(
                        Level.SEVERE,
                        "Ocurrió un error ejecutando la consulta --> {0}",
                        se.getMessage()
                );
            }
        }

        return false;
    }

    @Override
    public boolean actualizar(Evento anterior, Evento nuevo) {
        Connection conexion = conectar();

        if (conexion != null) {
            String sql = "UPDATE eventos SET nombre = ?, descripcion = ?, "
                    + "codigo_programa = ?, codigo_dependencia = ? "
                    + "WHERE consecutivo = ?";

            try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {

                preparedStatement.setString(1, nuevo.getNombre());
                preparedStatement.setString(2, nuevo.getDescripcion());

                if (nuevo.getCodigo_programa() == 0) {
                    preparedStatement.setNull(3, java.sql.Types.INTEGER);
                } else {
                    preparedStatement.setInt(3, nuevo.getCodigo_programa());
                }

                if (nuevo.getCodigo_dependencia() == 0) {
                    preparedStatement.setNull(4, java.sql.Types.INTEGER);
                } else {
                    preparedStatement.setInt(4, nuevo.getCodigo_dependencia());
                }

                preparedStatement.setInt(5, anterior.getConsecutivo());

                boolean resultado = preparedStatement.executeUpdate() > 0; // Se ejecuta la consulta

                Logger.getLogger(EventoMysqlRepositorio.class.getName()).log(
                        Level.INFO,
                        "Se ejecutó la consulta --> {0}",
                        preparedStatement.toString()
                );

                preparedStatement.close();
                conexion.close();

                return resultado;

            } catch (SQLException se) {

                Logger.getLogger(EventoMysqlRepositorio.class.getName()).log(
                        Level.SEVERE,
                        "Ocurrió un error ejecutando la consulta --> {0}",
                        se.getMessage()
                );
            }
        }

        return false;
    }

    @Override
    public boolean eliminar(Evento modelo) {
        Connection conexion = conectar();

        if (conexion != null) {
            String sql = "DELETE FROM eventos WHERE consecutivo = ?";

            try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {

                preparedStatement.setInt(1, modelo.getConsecutivo());

                boolean resultado = preparedStatement.executeUpdate() > 0; // Se ejecuta la consulta

                Logger.getLogger(EventoMysqlRepositorio.class.getName()).log(
                        Level.INFO,
                        "Se ejecutó la consulta --> {0}",
                        preparedStatement.toString()
                );

                preparedStatement.close();
                conexion.close();

                return resultado;

            } catch (SQLException se) {

                Logger.getLogger(EventoMysqlRepositorio.class.getName()).log(
                        Level.SEVERE,
                        "Ocurrió un error ejecutando la consulta --> {0}",
                        se.getMessage()
                );
            }
        }

        return false;
    }

    @Override
    public List<Evento> consultarTodo() {
        Connection conexion = conectar();

        List<Evento> eventos = new ArrayList<>();

        if (conexion != null) {
            String sql = "SELECT e.*,p.nombre as nombre_programa, d.nombre as nombre_dependencia FROM eventos e "
                    + "LEFT JOIN dependencias d ON e.codigo_dependencia = d.codigo "
                    + "LEFT JOIN programasAcademicos p ON e.codigo_programa = p.codigo";

            try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
                ResultSet resultado = preparedStatement.executeQuery();

                Logger.getLogger(EventoMysqlRepositorio.class.getName()).log(
                        Level.INFO,
                        "Se ejecutó la consulta --> {0}",
                        preparedStatement.toString()
                );

                while (resultado.next()) {
                    Dependencia dependencia = null;
                    Programa programa = null;

                    if (resultado.getInt("codigo_programa") > 0) {
                        programa = new Programa(
                                resultado.getInt("codigo_programa"),
                                resultado.getString("nombre_programa")
                        );
                    }

                    if (resultado.getInt("codigo_dependencia") > 0) {
                        dependencia = new Dependencia(
                                resultado.getInt("codigo_dependencia"),
                                resultado.getString("nombre_dependencia")
                        );
                    }
                   
                    eventos.add(
                            new Evento(
                                    resultado.getInt("consecutivo"),
                                    resultado.getString("nombre"),
                                    resultado.getString("descripcion"),
                                    programa,
                                    dependencia
                            )
                    );
                }

                conexion.close();
            } catch (SQLException se) {
                Logger.getLogger(EventoMysqlRepositorio.class.getName()).log(
                        Level.SEVERE,
                        "Ocurrió un error ejecutando la consulta --> {0}",
                        se.getMessage()
                );

                try {
                    conexion.close();
                } catch (SQLException sec) {
                    Logger.getLogger(EventoMysqlRepositorio.class.getName()).log(
                            Level.SEVERE,
                            "Ocurrió un error cerrando la conexión --> {0}",
                            sec.getMessage()
                    );
                }
            }
        }

        return eventos;
    }
}
