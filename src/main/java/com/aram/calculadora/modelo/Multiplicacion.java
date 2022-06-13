package com.aram.calculadora.modelo;

import java.math.BigDecimal;

// Nuestra 'Strategy' para multiplicar
// Por ser un detalle de implementacion, la clase es package private.
final class Multiplicacion implements Operacion {

    @Override
    public BigDecimal calcular(double primerOperando, double segundoOperando) {
        return BigDecimal.valueOf(primerOperando)
                .multiply(BigDecimal.valueOf(segundoOperando));
    }

    @Override
    public char signo() {
        return Simbolos.MULTIPLICACION.charAt(0);
    }
}