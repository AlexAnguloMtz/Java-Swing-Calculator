package com.aram.calculadora.modelo;

import java.math.BigDecimal;
import java.util.Collection;

//  La clase Calculadora realmente no calcula, delega la tarea
//  a las clases Strategy que implementan su interface Operacion
public final class Calculadora  {

    // Coleccion de clases 'Strategy'
    private final Collection<Operacion> operaciones;

    public Calculadora() {
        this.operaciones = java.util.List.of
                (new Suma(), new Resta(), new Multiplicacion(), new Division());
    }

    // Utilizamos un stream de nuestra coleccion de operaciones para localizar la implementacion requerida
    // segun el simbolo que sea pasado a este metodo, mapeamos al resultado numerico y devolvemos el resultado.
    // En caso de recibir un simbolo invalido lanzamos una excepcion.
    public BigDecimal calcular(char simbolo, double primerNumero, double segundoNumero) {
        return operaciones.stream()
                .filter(operacion -> operacion.signo() == simbolo)
                .map(operacion -> operacion.calcular(primerNumero, segundoNumero))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No existe la operacion con simbolo" + simbolo));
    }


}
