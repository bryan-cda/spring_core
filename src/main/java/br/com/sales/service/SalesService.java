package br.com.sales.service;

import br.com.sales.model.Sales;
import br.com.sales.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SalesService {
    private SalesRepository repository;

    @Autowired
    public SalesService(SalesRepository repository){
        this.repository = repository;
    }
    public List<Sales> listSales(Sales sale){
        return repository.list(sale);
    }
}
