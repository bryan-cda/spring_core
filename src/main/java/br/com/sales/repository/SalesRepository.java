package br.com.sales.repository;

import br.com.sales.model.Sales;
import org.springframework.stereotype.Repository;

import java.util.List;

import static java.util.Arrays.asList;

@Repository
public class SalesRepository {
    public List<Sales> list(Sales sales){
        return asList(sales);
    }
}
