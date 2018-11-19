package com.rad8329.simpleswingapp.infraestructura.zona;

import com.rad8329.simpleswingapp.dominio.zona.Zona;
import com.rad8329.simpleswingapp.infraestructura.zona.ZonaArchivoRepositorio;
import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.contentOf;

/**
 * @author rad8329
 */
public class ZonaArchivoRepositorioTest {

    private final String tituloArchivo = "test-zonas";

    @After
    public void tearDown() {
        //Eliminamos el archivo de pruebas
        File file = new File(tituloArchivo + ".txt");
        file.delete();
    }

    @Test
    public void testVerificarInsercionDeLineaEnArchivo() {

        ZonaArchivoRepositorio repositorio = new ZonaArchivoRepositorio(tituloArchivo);

        Zona zona = new Zona(
                1,
                "742 Evergreen Terrace",
                "Avenida SiempreViva 742",
                true,
                "10.10.15.12"
        );

        repositorio.insertar(zona);

        File actualFile = new File(tituloArchivo + ".txt");

        assertThat(contentOf(actualFile)).contains(ZonaArchivoRepositorio.zonaALineaCsv(zona));
    }

    @Test
    public void testVerificarActualizacionDeLineaEnArchivo() {

        ZonaArchivoRepositorio repositorio = new ZonaArchivoRepositorio(tituloArchivo);

        Zona zona1 = new Zona(
                1,
                "742 Evergreen Terrace",
                "Avenida SiempreViva 742",
                true,
                "10.10.15.12"
        );

        Zona zona2 = new Zona(
                2,
                "742 Evergreen Terrace",
                "Avenida SiempreViva 742",
                true,
                "10.10.15.13"
        );

        repositorio.insertar(zona1);

        repositorio.actualizar(zona1, zona2);

        File actualFile = new File(tituloArchivo + ".txt");

        assertThat(contentOf(actualFile)).doesNotContain(ZonaArchivoRepositorio.zonaALineaCsv(zona1));
        assertThat(contentOf(actualFile)).contains(ZonaArchivoRepositorio.zonaALineaCsv(zona2));
    }

    @Test
    public void testVerificarEliminacionDeLineaEnArchivo() {

        ZonaArchivoRepositorio repositorio = new ZonaArchivoRepositorio(tituloArchivo);

        Zona zona = new Zona(
                1,
                "742 Evergreen Terrace",
                "Avenida SiempreViva 742",
                true,
                "10.10.15.12"
        );

        repositorio.insertar(zona);

        repositorio.eliminar(zona);

        File actualFile = new File(tituloArchivo + ".txt");

        assertThat(contentOf(actualFile)).doesNotContain(ZonaArchivoRepositorio.zonaALineaCsv(zona));
    }

    @Test
    public void testVerificarQueLaConsultaEsteCompleta() {

        ZonaArchivoRepositorio repositorio = new ZonaArchivoRepositorio(tituloArchivo);

        Zona zona1 = new Zona(
                1,
                "742 Evergreen Terrace",
                "Avenida SiempreViva 742",
                true,
                "10.10.15.13"
        );

        Zona zona2 = new Zona(
                2,
                "742 Evergreen Terrace",
                "Avenida SiempreViva 742",
                true,
                "10.10.15.14"
        );

        Zona zona3 = new Zona(
                3,
                "742 Evergreen Terrace",
                "Avenida SiempreViva 742",
                false,
                "10.10.15.15"
        );

        repositorio.insertar(zona1);
        repositorio.insertar(zona2);
        repositorio.insertar(zona3);

        ArrayList<Zona> lista = repositorio.consultarTodo();

        assertThat(lista.get(0).getCodigo_zona()).isEqualTo(zona1.getCodigo_zona());
        assertThat(lista.get(1).getDireccion_ip_cerradura()).isEqualTo(zona2.getDireccion_ip_cerradura());
        assertThat(lista.get(2).tieneControl_de_acceso()).isEqualTo(zona3.tieneControl_de_acceso());
    }
}
