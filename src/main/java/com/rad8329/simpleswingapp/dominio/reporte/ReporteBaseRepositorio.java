package com.rad8329.simpleswingapp.dominio.reporte;

import com.rad8329.simpleswingapp.dominio.comun.repositorio.ConfiguracionJDBC;
import com.rad8329.simpleswingapp.dominio.comun.repositorio.RepositorioJDBC;
import com.rad8329.simpleswingapp.dominio.evento.EventoReporteValidador;
import com.rad8329.simpleswingapp.dominio.persona.PersonaReporteValidador;
import com.rad8329.simpleswingapp.dominio.zona.ZonaReporteValidador;
import java.util.List;
import org.json.JSONObject;

public abstract class ReporteBaseRepositorio extends RepositorioJDBC {

    public ReporteBaseRepositorio(ConfiguracionJDBC configuracion) {
        super(configuracion);
    }

    abstract public List<JSONObject> consultarPorgramasZona(ZonaReporteValidador criterio);

    abstract public List<JSONObject> consultarRecorridoEstudiante(PersonaReporteValidador criterio);

    abstract public List<JSONObject> consultarAsistenciaEvento(EventoReporteValidador criterio);
}
