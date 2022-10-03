package com.celtic.banking.controller;


import com.celtic.banking.request.ClientRequest;
import com.celtic.banking.response.ClientBalanceResponse;
import com.celtic.banking.service.ClientService;
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

    @GetMapping("/{id}")
    public ResponseEntity<ClientBalanceResponse> findClientById(@PathVariable Long id){
        return ResponseEntity.ok(clientService.findClientById(id));
    }

    @PostMapping
    public void addClient(@RequestBody ClientRequest clientRequest){
        clientService.createClient(clientRequest);
    }

    @DeleteMapping("/{id}")
    public void closeAccount(@PathVariable Long id){
        clientService.deleteClient(id);
    }

    @PutMapping
    public ResponseEntity<ClientBalanceResponse> updateClientDataAccount(@RequestBody ClientRequest clientRequest){
        //return clientService.updateClientDataAccount(clientRequest);
        return null;
    }
}
