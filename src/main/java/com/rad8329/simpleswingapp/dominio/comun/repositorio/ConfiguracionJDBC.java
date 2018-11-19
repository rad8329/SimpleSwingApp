package com.rad8329.simpleswingapp.dominio.comun.repositorio;

public class ConfiguracionJDBC {

    private final String usuario;
    private final String contrasena;
    private final String url;

    public ConfiguracionJDBC(String usuario, String contrasena, String url) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.url = url;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getUrl() {
        return url;
    }
}
