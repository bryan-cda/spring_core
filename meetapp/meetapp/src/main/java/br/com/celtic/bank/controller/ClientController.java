package br.com.celtic.bank.controller;

import br.com.celtic.bank.response.ClientBalanceResponse;
import br.com.celtic.bank.service.ClientService;
import br.com.celtic.bank.request.ClientRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("celtic-banking/clients")
@RestController
@AllArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<ClientBalanceResponse> listClients(){
        return ResponseEntity.ok(clientService.listClients());
    }

    @GetMapping("{/id}")
    public ResponseEntity<ClientBalanceResponse> findClientById(@PathVariable Long id){
        return ResponseEntity.ok(clientService.findClientById(id));
    }

    @PostMapping
    public void addClient(@RequestBody ClientRequest clientRequest){
        clientService.createAccount(clientRequest);
    }

    @DeleteMapping("{/id}")
    public void closeAccount(@PathVariable Long id){
        clientService.closeAccount(id);
    }

    @PutMapping
    public ResponseEntity<ClientBalanceResponse> updateClientDataAccount(@RequestBody ClientRequest clientRequest){
        //return clientService.updateClientDataAccount(clientRequest);
        return null;
    }
}
