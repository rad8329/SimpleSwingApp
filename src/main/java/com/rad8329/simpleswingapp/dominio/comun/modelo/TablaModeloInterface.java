package com.rad8329.simpleswingapp.dominio.comun.modelo;

import com.rad8329.simpleswingapp.dominio.comun.controlador.ControladorTablaModeloInterface;

public interface TablaModeloInterface {

    public void actualizarCuandoSeElimina(int indice);

    public void actualizarCuandoSeInserta(int indice);

    public void actualizarCuandoSeActualiza(int indice);

    public ControladorTablaModeloInterface getControlador();
}
