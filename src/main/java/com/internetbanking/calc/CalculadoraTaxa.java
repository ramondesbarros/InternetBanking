package com.internetbanking.calc;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CalculadoraTaxa {

    public BigDecimal calcularValorDoSaldoContandoTaxaAdmin(BigDecimal valorSaldoBancario, BigDecimal valorSaque, Boolean planoExclusive) throws IllegalArgumentException {

        if (planoExclusive) {

            BigDecimal resultado = valorSaldoBancario.subtract(valorSaque);

            return resultado;
        }

        if (valorSaque.intValue() > 100 && valorSaque.intValue() <= 300) {

            BigDecimal taxa = new BigDecimal("0.004");
            BigDecimal valorPorcentagem = valorSaldoBancario.multiply(taxa);
            BigDecimal resultado = valorSaldoBancario.subtract(valorSaque).subtract(valorPorcentagem);

            if (resultado.intValue() < 0) {

                throw new IllegalArgumentException();
            }

            return resultado;
        }

        if (valorSaque.intValue() > 300) {

            BigDecimal taxa = new BigDecimal("0.1");
            BigDecimal valorPorcentagem = valorSaldoBancario.multiply(taxa);
            BigDecimal resultado = valorSaldoBancario.subtract(valorSaque).subtract(valorPorcentagem);

            if (resultado.intValue() < 0) {

                throw new IllegalArgumentException();
            }

            return resultado;
        }

        BigDecimal resultado = valorSaldoBancario.subtract(valorSaque);

        return resultado;
    }
}
