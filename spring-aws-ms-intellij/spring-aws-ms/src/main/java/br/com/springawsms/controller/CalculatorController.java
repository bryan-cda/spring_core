package br.com.springawsms.controller;

import br.com.springawsms.model.Greeting;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("v1/greeting")
public class CalculatorController {

    public static final String greeting = "Hello, %s";

    public final AtomicLong id = new AtomicLong();

    @GetMapping
    public Greeting greeting(@RequestParam(name = "name", defaultValue = "world!") String name) {
        return new Greeting(id.getAndIncrement(), String.format(greeting, name));
    }

    @GetMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(@PathVariable ("numberOne") String numberOne,  @PathVariable ("numberTwo") String numberTwo){
       if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
           throw new NumberFormatException(String.format("Incorrect number passed: %s, %s", numberOne, numberTwo));
       }
        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }

    @GetMapping("/subtract/{numberOne}/{numberTwo}")
    public Double subtract(@PathVariable ("numberOne") String numberOne,  @PathVariable ("numberTwo") String numberTwo){
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
            throw new NumberFormatException(String.format("Incorrect number passed: %s, %s", numberOne, numberTwo));
        }
        return convertToDouble(numberOne) - convertToDouble(numberTwo);
    }

    @GetMapping("/division/{numberOne}/{numberTwo}")
    public Double division(@PathVariable ("numberOne") String numberOne,  @PathVariable ("numberTwo") String numberTwo){
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
            throw new NumberFormatException(String.format("Incorrect number passed: %s, %s", numberOne, numberTwo));
        }
        return convertToDouble(numberOne) / convertToDouble(numberTwo);
    }

    @GetMapping("/multiplication/{numberOne}/{numberTwo}")
    public Double multiplication(@PathVariable ("numberOne") String numberOne,  @PathVariable ("numberTwo") String numberTwo){
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
            throw new NumberFormatException(String.format("Incorrect number passed: %s, %s", numberOne, numberTwo));
        }
        return convertToDouble(numberOne) * convertToDouble(numberTwo);
    }

    @GetMapping("/square_root/{number}")
    public Double squareRoot(@PathVariable ("number") String number){
        if(!isNumeric(number)){
            throw new NumberFormatException(String.format("Incorrect number passed: %s", number));
        }
        return (Double) Math.sqrt(Double.parseDouble(number));
    }


    public Double convertToDouble(String number){
        if(Objects.isNull(number)) return 0D;
        number.replace(",", ".");
        if(isNumeric(number)) return Double.parseDouble(number);
        return 0D;
    }

    public Boolean isNumeric(String number){
        if(Objects.isNull(number)) return false;
        number.replace(",",".");
        return  number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
