package com.aram.calculadora.controlador;

import com.aram.calculadora.modelo.CalculadoraState;
import com.aram.calculadora.modelo.Simbolos;
import com.aram.calculadora.vista.VistaCalculadora;

// Primer 'State'. Es el estado por default, se mantendrá hasta que
// se presione un boton de operación matemática
public final class StateBasal implements CalculadoraState {

    private final VistaCalculadora gui;
    private boolean esPrimerDigito = true;

    public StateBasal(VistaCalculadora gui) {
        this.gui = gui;
        gui.establecerEstadoActivoBoton(Simbolos.IGUAL, false);
    }

    @Override
    public void manejarBotonNumerico(String numero) {
        if (soloSonCeros(numero)) {
            return;
        }
        if (!numero.equals(Simbolos.CERO) && gui.textoInferior().equals(Simbolos.CERO)) {
            gui.establecerTextoInferior(numero);
            esPrimerDigito = false;
            return;
        }
        if (esPrimerDigito) {
            gui.establecerTextoInferior(numero);
            esPrimerDigito = false;
            return;
        }
        gui.establecerTextoInferior(gui.textoInferior() + numero);
    }

    private boolean soloSonCeros(String numero) {
        return gui.textoInferior().equals(Simbolos.CERO)
                && numero.equals(Simbolos.CERO);
    }

    @Override
    public void manejarBotonSimboloPunto() {
        if (esPrimerDigito) {
            gui.establecerTextoInferior(Simbolos.CERO + Simbolos.PUNTO);
            esPrimerDigito = false;
        }
        if (!gui.textoInferior().contains(Simbolos.PUNTO)) {
            gui.establecerTextoInferior(gui.textoInferior() + Simbolos.PUNTO);
        }
    }

    @Override
    public void manejarBotonOperacion(String simboloOperacion) {
        gui.establecerTextoSuperior(gui.textoInferior() + Simbolos.ESPACIO + simboloOperacion);
        gui.establecerBotonesOperacionesActivados(false);
        gui.cambiarEstado(new StateOperacionActiva(gui));
    }

    @Override
    public void manejarBotonSimboloIgual() {
        //No hace nada en estado basal
    }
}