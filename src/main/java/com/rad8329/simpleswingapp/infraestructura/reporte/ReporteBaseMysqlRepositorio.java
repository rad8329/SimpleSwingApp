package com.rad8329.simpleswingapp.infraestructura.reporte;

import com.rad8329.simpleswingapp.infraestructura.zona.ZonaMysqlRepositorio;
import com.rad8329.simpleswingapp.dominio.comun.repositorio.ConfiguracionJDBC;
import com.rad8329.simpleswingapp.dominio.evento.EventoReporteValidador;
import com.rad8329.simpleswingapp.dominio.persona.PersonaReporteValidador;
import com.rad8329.simpleswingapp.dominio.reporte.ReporteBaseRepositorio;
import com.rad8329.simpleswingapp.dominio.zona.ZonaReporteValidador;
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
import org.json.JSONObject;

public class ReporteBaseMysqlRepositorio extends ReporteBaseRepositorio {

    public ReporteBaseMysqlRepositorio(ConfiguracionJDBC configuracion) {
        super(configuracion);
    }

    @Override
    public List<JSONObject> consultarPorgramasZona(ZonaReporteValidador criterio) {
        Connection conexion = conectar();

        List<JSONObject> registros = new ArrayList<>();

        if (conexion != null) {
            String sql = "SELECT count(p.codigo) numero_estudiantes ,pa.nombre programa FROM programasAcademicos pa "
                    + "INNER JOIN enrolamientos en ON en.codigo_programa=pa.codigo "
                    + "INNER JOIN personas p ON p.codigo = en.codigo_persona "
                    + "INNER JOIN ubicaciones u ON u.codigo_persona=p.codigo "
                    + "INNER JOIN lectoresRFID l ON l.serial = u.serial_lector "
                    + "INNER JOIN zonas z ON z.codigo_zona = l.codigo_zona "
                    + "WHERE z.codigo_zona = ? AND u.fecha >= ?";

            if (criterio.getFecha_final() != null && !criterio.getFecha_final().isEmpty()) {
                sql += " AND u.fecha < ?";
            }

            try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
                preparedStatement.setInt(1, criterio.getCodigo_zona());
                preparedStatement.setTimestamp(2, Timestamp.valueOf(criterio.getFecha_inicio() + ":00"));//Añadimos los sefundos

                if (criterio.getFecha_final() != null && !criterio.getFecha_final().isEmpty()) {
                    preparedStatement.setTimestamp(3, Timestamp.valueOf(criterio.getFecha_final() + ":00"));//Añadimos los sefundos
                }

                ResultSet resultado = preparedStatement.executeQuery();

                Logger.getLogger(ZonaMysqlRepositorio.class.getName()).log(
                        Level.INFO,
                        "Se ejecutó la consulta --> {0}",
                        preparedStatement.toString()
                );

                while (resultado.next()) {
                    JSONObject objeto = new JSONObject();

                    objeto
                            .put("numero_estudiantes", resultado.getInt("numero_estudiantes"))
                            .put("fecha", "")
                            .put("programa", resultado.getString("programa"));

                    registros.add(objeto);
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

        return registros;
    }

    @Override
    public List<JSONObject> consultarRecorridoEstudiante(PersonaReporteValidador criterio) {
        Connection conexion = conectar();

        List<JSONObject> registros = new ArrayList<>();

        if (conexion != null) {
            String sql = "SELECT p.codigo,z.nombre,u.fecha FROM ubicaciones u "
                    + "INNER JOIN personas p ON u.codigo_persona = p.codigo "
                    + "INNER JOIN lectoresRFID l ON l.serial = u.serial_lector "
                    + "INNER JOIN zonas z ON z.codigo_zona = l.codigo_zona "
                    + "WHERE p.codigo= ?  AND u.fecha >= ? ";

            if (criterio.getFecha_final() != null && !criterio.getFecha_final().isEmpty()) {
                sql += " AND u.fecha < ?";
            }

            try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
                preparedStatement.setInt(1, criterio.getCodigo_persona());
                preparedStatement.setTimestamp(2, Timestamp.valueOf(criterio.getFecha_inicio() + ":00"));//Añadimos los sefundos

                if (criterio.getFecha_final() != null && !criterio.getFecha_final().isEmpty()) {
                    preparedStatement.setTimestamp(3, Timestamp.valueOf(criterio.getFecha_final() + ":00"));//Añadimos los sefundos
                }

                ResultSet resultado = preparedStatement.executeQuery();

                Logger.getLogger(ZonaMysqlRepositorio.class.getName()).log(
                        Level.INFO,
                        "Se ejecutó la consulta --> {0}",
                        preparedStatement.toString()
                );

                while (resultado.next()) {
                    JSONObject objeto = new JSONObject();

                    objeto
                            .put("codigo_estudiante", resultado.getInt("codigo"))
                            .put("zona", resultado.getString("nombre"))
                            .put("fecha", DateTimeFormat.forPattern("yyyy-MM-dd hh:mm").print(resultado.getTimestamp("fecha").getTime()));

                    registros.add(objeto);
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

        return registros;
    }

    @Override
    public List<JSONObject> consultarAsistenciaEvento(EventoReporteValidador criterio) {
        Connection conexion = conectar();

        List<JSONObject> registros = new ArrayList<>();

        if (conexion != null) {
            String sql = "SELECT p.codigo, p.nombres, p.apellidos, u.fecha FROM ubicaciones u "
                    + "INNER JOIN personas p ON u.codigo_persona = p.codigo "
                    + "INNER JOIN lectoresRFID l ON l.serial = u.serial_lector "
                    + "INNER JOIN zonas z ON z.codigo_zona = l.codigo_zona "
                    + "INNER JOIN programaciones pr ON pr.codigo_zona = z.codigo_zona "
                    + "INNER JOIN eventos e ON e.consecutivo = pr.cod_evento "
                    + "WHERE e.consecutivo = ? AND u.fecha >= pr.fecha_inicio "
                    + "ORDER BY u.fecha";

            try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
                preparedStatement.setInt(1, criterio.getConsecutivo_evento());

                ResultSet resultado = preparedStatement.executeQuery();

                Logger.getLogger(ZonaMysqlRepositorio.class.getName()).log(
                        Level.INFO,
                        "Se ejecutó la consulta --> {0}",
                        preparedStatement.toString()
                );

                while (resultado.next()) {
                    JSONObject objeto = new JSONObject();

                    objeto
                            .put("codigo", resultado.getInt("codigo"))
                            .put("nombres", resultado.getString("nombres"))
                            .put("apellidos", resultado.getString("apellidos"))
                            .put("fecha", DateTimeFormat.forPattern("yyyy-MM-dd hh:mm").print(resultado.getTimestamp("fecha").getTime()));

                    registros.add(objeto);
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

        return registros;
    }
}
