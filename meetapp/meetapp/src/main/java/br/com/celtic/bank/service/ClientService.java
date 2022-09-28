package br.com.celtic.bank.service;

import br.com.celtic.bank.request.ClientRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
@Service
public class ClientService {
    public Map<String, BigDecimal> getBalance(ClientRequest clientRequest) {
        return Map.of("Client: Bryan Duarte", BigDecimal.valueOf(35000));
    }
}
