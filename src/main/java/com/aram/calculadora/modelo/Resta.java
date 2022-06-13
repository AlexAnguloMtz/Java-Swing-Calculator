package com.aram.calculadora.modelo;

import java.math.BigDecimal;

 //Nuestra 'Strategy' para restar
// Por ser un detalle de implementacion, la clase es package private.
final class Resta implements Operacion {

    @Override
    public BigDecimal calcular(double primerOperando, double segundoOperando) {
        return BigDecimal.valueOf(primerOperando)
                .subtract(BigDecimal.valueOf(segundoOperando));
    }

    @Override
    public char signo() {
        return Simbolos.RESTA.charAt(0);
    }
}
