package br.com.spring.meetapp.controller;

import br.com.spring.meetapp.converter.NumberConverter;
import br.com.spring.meetapp.model.Greeting;
import br.com.spring.meetapp.operations.MathOperations;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("v1/calculator")
public class CalculatorController {

    private final NumberConverter converter;
    private final MathOperations operations;

    public CalculatorController(NumberConverter converter, MathOperations operations){
        this.converter = converter;
        this.operations = operations;
    }

    public static final String greeting = "Hello, %s";

    public final AtomicLong id = new AtomicLong();

    @GetMapping
    public Greeting greeting(@RequestParam(name = "name", defaultValue = "world!") String name) {
        return new Greeting(id.getAndIncrement(), String.format(greeting, name));
    }

    @GetMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(@PathVariable ("numberOne") String numberOne,  @PathVariable ("numberTwo") String numberTwo){
       if(!converter.isNumeric(numberOne) || !converter.isNumeric(numberTwo)){
           throw new NumberFormatException(String.format("Incorrect number passed: %s, %s", numberOne, numberTwo));
       }
        return operations.sum(converter.convertToDouble(numberOne), converter.convertToDouble(numberTwo));
    }

    @GetMapping("/subtract/{numberOne}/{numberTwo}")
    public Double subtract(@PathVariable ("numberOne") String numberOne,  @PathVariable ("numberTwo") String numberTwo){
        if(!converter.isNumeric(numberOne) || !converter.isNumeric(numberTwo)){
            throw new NumberFormatException(String.format("Incorrect number passed: %s, %s", numberOne, numberTwo));
        }
        return  operations.subtract(converter.convertToDouble(numberOne), converter.convertToDouble(numberTwo));
    }

    @GetMapping("/division/{numberOne}/{numberTwo}")
    public Double division(@PathVariable ("numberOne") String numberOne,  @PathVariable ("numberTwo") String numberTwo){
        if(!converter.isNumeric(numberOne) || !converter.isNumeric(numberTwo)){
            throw new NumberFormatException(String.format("Incorrect number passed: %s, %s", numberOne, numberTwo));
        }
        return  operations.division(converter.convertToDouble(numberOne), converter.convertToDouble(numberTwo));
    }

    @GetMapping("/multiplies/{numberOne}/{numberTwo}")
    public Double multiplies(@PathVariable ("numberOne") String numberOne,  @PathVariable ("numberTwo") String numberTwo){
        if(!converter.isNumeric(numberOne) || !converter.isNumeric(numberTwo)){
            throw new NumberFormatException(String.format("Incorrect number passed: %s, %s", numberOne, numberTwo));
        }
        return operations.multiplies(converter.convertToDouble(numberOne), converter.convertToDouble(numberTwo));
    }

    @GetMapping("/square_root/{number}")
    public Double squareRoot(@PathVariable ("number") String number){
        if(!converter.isNumeric(number)){
            throw new NumberFormatException(String.format("Incorrect number passed: %s", number));
        }
        return  operations.squareRoot(converter.convertToDouble(number));
    }
}
