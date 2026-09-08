package service;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class calcularAluguelTeste {

    @Test
    public void deveCalcularValorDoAluguel() {
        CalculoAluguel calculo = new CalculoAluguel();

        double precoDiaria = 5.00;

        LocalDate inicio = LocalDate.of(2026, 9, 10);
        LocalDate devolucao = LocalDate.of(2026, 9, 13);

        double resultado = calculo.calcularValor(
                precoDiaria,
                inicio,
                devolucao
        );

        assertEquals(15.00, resultado, 0.001);
    }
}
