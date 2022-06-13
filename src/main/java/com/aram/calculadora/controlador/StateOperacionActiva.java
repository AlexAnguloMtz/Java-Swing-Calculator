package com.aram.calculadora.controlador;

import com.aram.calculadora.modelo.CalculadoraState;
import com.aram.calculadora.modelo.Operacion;
import com.aram.calculadora.modelo.Simbolos;
import com.aram.calculadora.vista.VistaCalculadora;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Segundo 'State'. Hay una operación activa y así será hasta completarla.
public final class StateOperacionActiva implements CalculadoraState {

    private final VistaCalculadora gui;
    private boolean esPrimerDigito = true;

    StateOperacionActiva(VistaCalculadora gui) {
        this.gui = gui;
        gui.establecerEstadoActivoBoton(Simbolos.IGUAL, true);
    }

    @Override
    public void manejarBotonNumerico(String numero) {
        if (esPrimerDigito) {
            gui.establecerTextoInferior(numero);
            esPrimerDigito = false;
            return;
        }
        if (gui.textoInferior().equals(Simbolos.CERO)) {
            gui.establecerTextoInferior(numero);
            return;
        }
        gui.establecerTextoInferior(gui.textoInferior() + numero);
    }

    @Override
    public void manejarBotonSimboloPunto() {
        if (esPrimerDigito) {
            gui.establecerTextoInferior(Simbolos.CERO + Simbolos.PUNTO);
            esPrimerDigito = false;
            return;
        }
        if (!gui.textoInferior().contains(Simbolos.PUNTO)) {
            gui.establecerTextoInferior(gui.textoInferior() + Simbolos.PUNTO);
        }
    }

    @Override
    public void manejarBotonOperacion(String multiplicacion) { }

    @Override
    public void manejarBotonSimboloIgual() {
        try {
            mostrarResultadoOperacion();
        } catch (ArithmeticException excepcion) {
            if (fueDivisionEntreCero(excepcion)) {
                gui.informarDivisionEntreCero();
            }
        }
        gui.cambiarAEstadoBasal();
    }

    private void mostrarResultadoOperacion() {
        double primerNumero = parsearNumeroTextoSuperior(gui);
        double segundoNumero = parsearNumeroTextoInferior(gui);
        char simbolo = parsearUltimoSimboloEnTextoSuperior(gui);
        gui.establecerTextoInferior(calcular(simbolo, primerNumero, segundoNumero).toString());
    }

    private BigDecimal calcular(char simbolo,
                                double primerNumero,
                                double segundoNumero) throws ArithmeticException {

        return calculadora().calcular(simbolo, primerNumero, segundoNumero);
    }

    private boolean fueDivisionEntreCero(ArithmeticException e) {
        return e.getMessage().equals(Operacion.ERROR_DIVISION_ENTRE_CERO);
    }

    private double parsearNumeroTextoInferior(VistaCalculadora gui) {
        return Double.parseDouble(gui.textoInferior());
    }

    private double parsearNumeroTextoSuperior(VistaCalculadora gui) {
        String regex = "(-)?\\d+(\\.)?\\d*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(gui.textoSuperior());
        if (matcher.find()) {
            return Double.parseDouble(matcher.group());
        }
        throw new IllegalStateException("No se pudo parsear numero");
    }

    private char parsearUltimoSimboloEnTextoSuperior(VistaCalculadora gui) {
        return gui.textoSuperior().charAt(gui.textoSuperior().length() - 1);
    }
}