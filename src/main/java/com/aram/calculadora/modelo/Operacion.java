package com.aram.calculadora.modelo;

import java.math.BigDecimal;

// Patrón de diseño 'Strategy'.
// Interfaz base que implementan todas las operaciones de la calculadora.

public interface Operacion {

        String ERROR_DIVISION_ENTRE_CERO = "Division entre cero";

        BigDecimal calcular(double primerOperando, double segundoOperando);

        char signo();

}


