package com.aram.calculadora.modelo;


import java.math.BigDecimal;

// Nuestra 'Strategy' para sumar
// Por ser un detalle de implementacion, la clase es package private
final class Suma implements Operacion {

    @Override
    public BigDecimal calcular(double primerOperando, double segundoOperando) {
        return BigDecimal.valueOf(primerOperando)
                .add(BigDecimal.valueOf(segundoOperando));
    }

    @Override
    public char signo() {
        return Simbolos.SUMA.charAt(0);
    }
}