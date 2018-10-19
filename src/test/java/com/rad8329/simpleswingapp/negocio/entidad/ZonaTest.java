package com.rad8329.simpleswingapp.negocio.entidad;

import com.rad8329.simpleswingapp.negocio.excepcion.ValidacionExcepcion;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author rad8329
 */
public class ZonaTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testValidarQueCodigoDebeSerUnNumeroPositivo() throws ValidacionExcepcion {

        thrown.expect(ValidacionExcepcion.class);

        thrown.expectMessage("El código debe ser mayor a cero");

        Zona instancia = new Zona(-1, "", "", true, "");

        instancia.validar();
    }

    @Test
    public void testValidarQueCodigoEsNecesario() throws ValidacionExcepcion {

        thrown.expect(ValidacionExcepcion.class);

        thrown.expectMessage("El código debe ser mayor a cero");

        Zona instancia = new Zona(0, "", "", true, "");

        instancia.validar();
    }

    @Test
    public void testValidarQueNombreEsNecesario() throws ValidacionExcepcion {

        thrown.expect(ValidacionExcepcion.class);

        thrown.expectMessage("El nombre no debe ser vacio");

        Zona instancia = new Zona(1, "", "", true, "");

        instancia.validar();
    }

    @Test
    public void testValidarQueDescripcionEsNecesario() throws ValidacionExcepcion {

        thrown.expect(ValidacionExcepcion.class);

        thrown.expectMessage("La descripción no debe ser vacio");

        Zona instancia = new Zona(1, "742 Evergreen Terrace", "", true, "");

        instancia.validar();
    }

    @Test
    public void testValidarCuandoDireccionIpEsNecesaria() throws ValidacionExcepcion {

        thrown.expect(ValidacionExcepcion.class);

        thrown.expectMessage("La dirección IP no debe ser vacio");

        Zona instancia = new Zona(1, "742 Evergreen Terrace", "Avenida SiempreViva 742", true, "");

        instancia.validar();
    }

    @Test
    public void testValidarCuandoDireccionIpNoEsNecesaria() throws ValidacionExcepcion {

        thrown.expect(ValidacionExcepcion.class);

        thrown.expectMessage("El control de acceso debe estar habilitado");

        Zona instancia = new Zona(1, "742 Evergreen Terrace", "Avenida SiempreViva 742", false, "10.10.15.12");

        instancia.validar();
    }

    @Test
    public void testValidarQueDireccionIpEsIvalida() throws ValidacionExcepcion {

        thrown.expect(ValidacionExcepcion.class);

        thrown.expectMessage("La dirección IP no es válida");

        Zona instancia = new Zona(1, "742 Evergreen Terrace", "Avenida SiempreViva 742", true, "10.10.15.12888");

        instancia.validar();
    }
}
