package br.com.springawsms.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.springawsms.Greeting;

@RestController
@RequestMapping("/v1/greeting")
public class GreetingController {
	
	private static final String message = "Hello, %s";
	
	private final AtomicLong id = new AtomicLong();
	
	@GetMapping
    public Greeting greeting(@RequestParam(name = "name", defaultValue = "world!") String name) {
        return new Greeting(id.getAndIncrement(), String.format(message, name));
    }
	
}
