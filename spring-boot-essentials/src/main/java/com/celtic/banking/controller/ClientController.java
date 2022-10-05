package com.celtic.banking.controller;


import com.celtic.banking.mapping.ClientMapper;
import com.celtic.banking.request.ClientRequest;
import com.celtic.banking.request.ClientResponse;
import com.celtic.banking.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("celtic-banking/clients")
@RestController
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientResponse>> listClients(){
        return ResponseEntity.ok(clientService.listClients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> findClientById(@PathVariable Long id){
         return ResponseEntity.ok(clientService.findClientById(id));
    }

    @PostMapping
    public  ClientResponse createClient(@RequestBody ClientRequest clientRequest){
        return clientService.createClient(ClientMapper.INSTANCE.mapToClient(clientRequest));
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id){
        clientService.deleteClient(id);
    }

    @PutMapping
    public ResponseEntity<ClientResponse> updateClientData(@RequestBody ClientRequest clientRequest){
        return ResponseEntity.ok(clientService.updateClientData(clientRequest));
    }
}
