package com.aram.calculadora.modelo;

// Interfaz patrón de diseño 'State' para la Calculadora.
// Implementamos la calculadora como una maquina de estado finito ("finite state machine").

public interface CalculadoraState {

    void manejarBotonNumerico(String numero);

    void manejarBotonSimboloPunto();

    void manejarBotonOperacion(String simboloOperacion);

    void manejarBotonSimboloIgual();

    default Calculadora calculadora() {
        return new Calculadora();
    }

}