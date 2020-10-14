package br.com.sales;

import br.com.sales.model.Sales;
import br.com.sales.service.SalesService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    private SalesService salesService;

    public App(SalesService salesService) {
        this.salesService = salesService;
    }

    @GetMapping
    public String hello(){
        return applicationName;
    }

    @GetMapping("/sales")
    public ResponseEntity listSales(){
        return new ResponseEntity(salesService.listSales(new Sales(applicationProduct, Float.valueOf(applicationPrice), LocalDate.now(), applicationName)),HttpStatus.CREATED);
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
