package com.aram.calculadora.modelo;

import java.math.BigDecimal;
import java.math.RoundingMode;

// Nuestra 'Strategy' para dividir.
// Por ser un detalle de implementacion, la clase es package private.
final class Division implements Operacion {

    // Necesitamos configurar este metodo
    // para que no tire excepciones al hacer divisiones inexactas
    //
    //Tambien debemos lanzar una excepcion al dividir entre cero
    @Override
    public BigDecimal calcular(double dividendo, double divisor) throws ArithmeticException {
        if (divisor == 0) {
            throw new ArithmeticException(ERROR_DIVISION_ENTRE_CERO);
        }
        return BigDecimal.valueOf(dividendo)
                .divide(BigDecimal.valueOf(divisor), 2, RoundingMode.HALF_UP);
    }

    @Override
    public char signo() {
        return Simbolos.DIVISION.charAt(0);
    }

}