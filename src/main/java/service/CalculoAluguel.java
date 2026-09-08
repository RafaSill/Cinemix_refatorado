package service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CalculoAluguel {
    public double calcularValor (
            double precoDiaria,
            LocalDate dataInicio,
            LocalDate dataDevolucao){
        
        long dias = ChronoUnit.DAYS.between(dataInicio, dataDevolucao);
        
        return precoDiaria * dias;
    }
    
}
