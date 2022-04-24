package br.com.spring.meetapp.converter;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class NumberConverter {
    public Double convertToDouble(String number){
        if(Objects.isNull(number)) return 0D;
        number.replace(",", ".");
        if(isNumeric(number)) return Double.parseDouble(number);
        return 0D;
    }

    public Boolean isNumeric(String number){
        if(Objects.isNull(number)) return false;
        number.replace(",",".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }


}
