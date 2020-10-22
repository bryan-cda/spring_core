package br.com.sales;

import br.com.sales.model.Sales;
import br.com.sales.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;


@SpringBootApplication
@RestController
public class App {

    @Value("${application.name}")
    private String applicationName;

    @Value("${application.product}")
    private String applicationProduct;

    @Value("${application.price}")
    private String applicationPrice;

    private final String loggedIn;

    private SalesService salesService;


    public App(SalesService salesService, @Qualifier("loggedIn") String loggedIn) {
        this.salesService = salesService;
        this.loggedIn = loggedIn;
    }

    @GetMapping
    public String hello(){
        return applicationName;
    }

    @GetMapping("/sales")
    public ResponseEntity listSales(){
        return new ResponseEntity(salesService.listSales(new Sales(applicationProduct, Float.valueOf(applicationPrice), LocalDate.now(), applicationName)),HttpStatus.CREATED);
    }

    @GetMapping("/logged")
    public String getLoggedIn(){
        return loggedIn;
    }


    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }




}


