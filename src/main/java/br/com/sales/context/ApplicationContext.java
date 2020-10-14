package br.com.sales.context;

import br.com.sales.model.Sales;
import br.com.sales.repository.SalesRepository;
import br.com.sales.service.SalesService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationContext {
    @Bean
    public SalesService salesService(
            SalesRepository repository) {
        return new SalesService(repository);
    }

}
