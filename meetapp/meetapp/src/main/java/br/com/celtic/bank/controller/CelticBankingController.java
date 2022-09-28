package br.com.celtic.bank.controller;

import br.com.celtic.bank.service.ClientService;
import br.com.celtic.bank.request.ClientRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

@RequestMapping("celtic-banking")
@RestController
@AllArgsConstructor
public class CelticBankingController {
    private final ClientService clientService;

    @GetMapping("/balance/client")
    public ResponseEntity<Map<String, BigDecimal>> getBalance(@RequestBody ClientRequest clientRequest){
        return ResponseEntity.ok(clientService.getBalance(clientRequest));
    }
}
