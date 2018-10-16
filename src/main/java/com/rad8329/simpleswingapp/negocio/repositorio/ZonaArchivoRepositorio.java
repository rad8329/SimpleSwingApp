package com.rad8329.simpleswingapp.negocio.repositorio;

import com.rad8329.simpleswingapp.negocio.entidad.Zona;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * El manejo de archivos hechos acá no es el mejor, pero es simplemente para
 * hacer el primer ejemplo de repositorio
 *
 * @author rad8329
 */
public class ZonaArchivoRepositorio implements RepositorioInterface<Zona> {

    private final File archivo;
    private final String nombreArchivo;

    public ZonaArchivoRepositorio(String tituloArchivo) {
        nombreArchivo = tituloArchivo + ".txt";

        archivo = new File(nombreArchivo);
    }

    @Override
    public ArrayList<Zona> consultarTodo() {
        ArrayList<Zona> zonas = new ArrayList<>();//Limpiamos

        try {
            FileReader manejador = new FileReader(this.archivo);

            try (BufferedReader buffer = new BufferedReader(manejador)) {
                String linea;
                int contador = 0;

                while ((linea = buffer.readLine()) != null) {
                    contador++;

                    try {
                        zonas.add(lineaCsvAZona(linea));
                    } catch (ArrayIndexOutOfBoundsException |
                            java.lang.NumberFormatException ex) {

                        Logger.getLogger(ZonaArchivoRepositorio.class.getName()).log(
                                Level.WARNING,
                                String.format(
                                        "[%s]Error cargando la línea %d: %s",
                                        nombreArchivo,
                                        contador,
                                        ex.getMessage()
                                )
                        );
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(ZonaArchivoRepositorio.class.getName()).log(
                        Level.SEVERE,
                        ex.getMessage()
                );
            }

            Logger.getLogger(ZonaArchivoRepositorio.class.getName()).log(
                    Level.INFO,
                    String.format(
                            "[%s]Las zonas fueron cargadas desde el archivo",
                            nombreArchivo
                    )
            );

        } catch (IOException ex) {
            Logger.getLogger(ZonaArchivoRepositorio.class.getName()).log(
                    Level.WARNING,
                    ex.getMessage()
            );
        }

        return zonas;
    }

    @Override
    public boolean insertar(Zona modelo) {
        try {
            FileWriter manejador = new FileWriter(this.archivo, true);

            try (BufferedWriter buffer = new BufferedWriter(manejador)) {
                buffer.append(zonaALineaCsv(modelo));
            } catch (IOException ex) {
                Logger.getLogger(ZonaArchivoRepositorio.class.getName()).log(
                        Level.SEVERE,
                        ex.getMessage()
                );
            }

        } catch (IOException ex) {
            Logger.getLogger(ZonaArchivoRepositorio.class.getName()).log(
                    Level.SEVERE,
                    ex.getMessage()
            );
        }

        Logger.getLogger(ZonaArchivoRepositorio.class.getName()).log(
                Level.INFO,
                String.format(
                        "[%s]La zona fue guardada en el archivo",
                        nombreArchivo
                )
        );

        return true;
    }

    /**
     * Si se quiere eliminar la línea, se pasa el parametro contenidoNuevo vacío
     *
     * @param lineaABuscar   Linea en formato csv
     * @param contenidoNuevo Linea en formato csv
     * @return true = pudo guardar, false = no pudo guardar
     */
    private boolean actualizarLinea(String lineaABuscar, String contenidoNuevo) {
        try {
            File archivoTemporal = new File("temp-" + nombreArchivo);

            FileReader manejadorLectura = new FileReader(this.archivo);
            FileWriter manejadorEscritura = new FileWriter(archivoTemporal);

            try (BufferedReader buffer = new BufferedReader(manejadorLectura);
                 BufferedWriter writer = new BufferedWriter(manejadorEscritura)) {

                String linea;

                while ((linea = buffer.readLine()) != null) {
                    if (linea.trim().contains(lineaABuscar)) {
                        //Si la línea es igual, la cambiamos en el archivo temporal
                        if (contenidoNuevo.isEmpty()) {
                            Logger.getLogger(ZonaArchivoRepositorio.class.getName()).log(
                                    Level.INFO,
                                    String.format(
                                            "[%s]La zona fue eliminada con éxito",
                                            nombreArchivo
                                    )
                            );

                        } else {
                            writer.write(contenidoNuevo);

                            Logger.getLogger(ZonaArchivoRepositorio.class.getName()).log(
                                    Level.INFO,
                                    String.format(
                                            "[%s]La zona fue actulizada con éxito",
                                            nombreArchivo
                                    )
                            );
                        }
                    } else {
                        writer.write(linea + System.getProperty("line.separator"));
                    }
                }
            }

            return archivoTemporal.renameTo(this.archivo);
        } catch (IOException ex) {
            Logger.getLogger(ZonaArchivoRepositorio.class.getName()).log(
                    Level.SEVERE,
                    ex.getMessage()
            );
        }

        return true;
    }

    @Override
    public boolean actualizar(Zona anterior, Zona nuevo) {

        actualizarLinea(zonaALineaCsv(anterior).trim(), zonaALineaCsv(nuevo));

        return true;
    }

    @Override
    public boolean eliminar(Zona modelo) {

        actualizarLinea(zonaALineaCsv(modelo).trim(), "");

        return true;
    }

    /**
     * Serializa una zona como CSV
     *
     * @param zona Un objeto de tipo Zona
     * @return Una línea en formato CSV
     */
    private String zonaALineaCsv(Zona zona) {
        return String.format(
                "%d;%s;%s;%s;%s",
                zona.getCodigo_zona(),
                //Quitamos el ; que nos puede hacer fallar
                zona.getNombre().replace(";", ""),
                //Quitamos el ; que nos puede hacer fallar
                zona.getDescripcion().replace(";", ""),
                Boolean.toString(zona.tieneControl_de_acceso()),
                //Quitamos el ; que nos puede hacer fallar
                zona.getDireccion_ip_cerradura().replace(";", "")
        ) + System.getProperty("line.separator");
    }

    /**
     * Instancia un objeto de tipo Zona a partir de una linea CSV
     *
     * @param linea Una línea en formato CSV
     * @return Un objeto de tipo Zona
     */
    private Zona lineaCsvAZona(String linea) {
        String[] datos = linea.split(";", -1);

        return new Zona(
                Integer.parseInt(datos[0]),
                datos[1], datos[2],
                Boolean.parseBoolean(datos[3]),
                datos[4]
        );
    }
}
