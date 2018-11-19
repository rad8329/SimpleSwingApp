package com.rad8329.simpleswingapp.infraestructura.evento;

import com.rad8329.simpleswingapp.dominio.comun.repositorio.ConfiguracionJDBC;
import com.rad8329.simpleswingapp.dominio.comun.repositorio.RepositorioCrudInterface;
import com.rad8329.simpleswingapp.dominio.comun.repositorio.RepositorioJDBC;
import com.rad8329.simpleswingapp.dominio.evento.Evento;
import com.rad8329.simpleswingapp.dominio.evento.Programacion;
import com.rad8329.simpleswingapp.dominio.zona.Zona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.format.DateTimeFormat;

public class ProgramacionMysqlRepositorio extends RepositorioJDBC implements RepositorioCrudInterface<Programacion> {

    public ProgramacionMysqlRepositorio(ConfiguracionJDBC configuracion) {
        super(configuracion);
    }

    @Override
    public boolean insertar(Programacion modelo) {
        Connection conexion = conectar();

        if (conexion != null) {
            String sql = "INSERT INTO programaciones (codigo_zona, fecha_inicio, fecha_fin, cod_evento, actividades) "
                    + "VALUES(?,?,?,?,?)";

            try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {

                preparedStatement.setInt(1, modelo.getCodigo_zona());
                preparedStatement.setTimestamp(
                        2,
                        Timestamp.valueOf(modelo.getFecha_inicio() + ":00")
                );//Añadimos los sefundos

                preparedStatement.setTimestamp(
                        3,
                        Timestamp.valueOf(modelo.getFecha_fin() + ":00")
                );//Añadimos los sefundos

                preparedStatement.setInt(4, modelo.getCod_evento());

                if (modelo.getActividades() == null || modelo.getActividades().isEmpty()) {
                    preparedStatement.setNull(5, java.sql.Types.VARCHAR);
                } else {
                    preparedStatement.setString(5, modelo.getActividades());
                }

                boolean resultado = preparedStatement.executeUpdate() > 0; // Se ejecuta la consulta

                Logger.getLogger(ProgramacionMysqlRepositorio.class.getName()).log(
                        Level.INFO,
                        "Se ejecutó la consulta --> {0}",
                        preparedStatement.toString()
                );

                preparedStatement.close();
                conexion.close();

                return resultado;

            } catch (SQLException se) {

                Logger.getLogger(ProgramacionMysqlRepositorio.class.getName()).log(
                        Level.SEVERE,
                        "Ocurrió un error ejecutando la consulta --> {0}",
                        se.getMessage()
                );
            }
        }

        return false;
    }

    @Override
    public boolean actualizar(Programacion anterior, Programacion nuevo) {
        Connection conexion = conectar();

        if (conexion != null) {
            String sql = "UPDATE programaciones SET fecha_inicio = ?, fecha_fin = ?, "
                    + "actividades = ?, codigo_zona = ? "
                    + "WHERE consecutivo = ?";

            try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {

                preparedStatement.setTimestamp(
                        1,
                        Timestamp.valueOf(nuevo.getFecha_inicio() + ":00")
                ); // Añadimos los segundos
                preparedStatement.setTimestamp(
                        2,
                        Timestamp.valueOf(nuevo.getFecha_fin() + ":00")
                );// Añadimos los segundos

                if (nuevo.getActividades() == null || nuevo.getActividades().isEmpty()) {
                    preparedStatement.setNull(3, java.sql.Types.VARCHAR);
                } else {
                    preparedStatement.setString(3, nuevo.getActividades());
                }

                preparedStatement.setInt(4, nuevo.getCodigo_zona());
                preparedStatement.setInt(5, anterior.getConsecutivo());

                boolean resultado = preparedStatement.executeUpdate() > 0; // Se ejecuta la consulta

                Logger.getLogger(ProgramacionMysqlRepositorio.class.getName()).log(
                        Level.INFO,
                        "Se ejecutó la consulta --> {0}",
                        preparedStatement.toString()
                );

                preparedStatement.close();
                conexion.close();

                return resultado;

            } catch (SQLException se) {

                Logger.getLogger(ProgramacionMysqlRepositorio.class.getName()).log(
                        Level.SEVERE,
                        "Ocurrió un error ejecutando la consulta --> {0}",
                        se.getMessage()
                );
            }
        }

        return false;
    }

    @Override
    public boolean eliminar(Programacion modelo) {
        Connection conexion = conectar();

        if (conexion != null) {
            String sql = "DELETE FROM programaciones WHERE consecutivo = ?";

            try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {

                preparedStatement.setInt(1, modelo.getConsecutivo());

                boolean resultado = preparedStatement.executeUpdate() > 0; // Se ejecuta la consulta

                Logger.getLogger(ProgramacionMysqlRepositorio.class.getName()).log(
                        Level.INFO,
                        "Se ejecutó la consulta --> {0}",
                        preparedStatement.toString()
                );

                preparedStatement.close();
                conexion.close();

                return resultado;

            } catch (SQLException se) {

                Logger.getLogger(ProgramacionMysqlRepositorio.class.getName()).log(
                        Level.SEVERE,
                        "Ocurrió un error ejecutando la consulta --> {0}",
                        se.getMessage()
                );
            }
        }

        return false;
    }

    @Override
    public List<Programacion> consultarTodo() {
        Connection conexion = conectar();

        List<Programacion> programaciones = new ArrayList<>();

        if (conexion != null) {
            String sql = "SELECT p.*, e.nombre AS evento, z.nombre AS zona FROM programaciones p "
                    + "JOIN zonas z ON z.codigo_zona = p.codigo_zona "
                    + "JOIN eventos e ON e.consecutivo = p.cod_evento";

            try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
                ResultSet resultado = preparedStatement.executeQuery();

                Logger.getLogger(ProgramacionMysqlRepositorio.class.getName()).log(
                        Level.INFO,
                        "Se ejecutó la consulta --> {0}",
                        preparedStatement.toString()
                );

                while (resultado.next()) {
                    programaciones.add(
                            new Programacion(
                                    resultado.getInt("consecutivo"),
                                    DateTimeFormat.forPattern("yyyy-MM-dd hh:mm").print(resultado.getTimestamp("fecha_inicio").getTime()),
                                    DateTimeFormat.forPattern("yyyy-MM-dd hh:mm").print(resultado.getTimestamp("fecha_fin").getTime()),
                                    resultado.getString("actividades"),
                                    new Evento(resultado.getInt("codigo_zona"), resultado.getString("zona")),
                                    new Zona(resultado.getInt("codigo_zona"), resultado.getString("zona"))
                            )
                    );
                }

                conexion.close();
            } catch (SQLException se) {
                Logger.getLogger(ProgramacionMysqlRepositorio.class.getName()).log(
                        Level.SEVERE,
                        "Ocurrió un error ejecutando la consulta --> {0}",
                        se.getMessage()
                );

                try {
                    conexion.close();
                } catch (SQLException sec) {
                    Logger.getLogger(ProgramacionMysqlRepositorio.class.getName()).log(
                            Level.SEVERE,
                            "Ocurrió un error cerrando la conexión --> {0}",
                            sec.getMessage()
                    );
                }
            }
        }

        return programaciones;
    }
}
