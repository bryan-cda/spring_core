package br.com.springawsms.operations;

import org.springframework.stereotype.Component;

@Component
public class MathOperations {
    public Double sum(Double numberOne, Double numberTwo){
        return numberOne + numberTwo;
    }

    public Double subtract(Double numberOne, Double numberTwo){
        return numberOne - numberTwo;
    }

    public Double multiplies(Double numberOne, Double numberTwo){
        return numberOne * numberTwo;
    }

    public Double division(Double numberOne, Double numberTwo){
        return numberOne * numberTwo;
    }

    public Double squareRoot(Double number){
        return (Double) Math.sqrt(number);
    }
}
