package br.com.springawsms.controller;

import br.com.springawsms.model.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("v1/greeting")
public class GreetingController {

    public static final String greeting = "Hello, %s";

    public final AtomicLong id = new AtomicLong();

    @GetMapping
    public Greeting greeting(@RequestParam(name = "name", defaultValue = "world!") String name) {
        return new Greeting(id.getAndIncrement(), String.format(greeting, name));
    }
}
