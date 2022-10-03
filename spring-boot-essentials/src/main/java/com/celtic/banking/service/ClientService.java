package com.celtic.banking.service;

import com.celtic.banking.domain.Client;
import com.celtic.banking.repository.ClientRepository;
import com.celtic.banking.request.ClientRequest;
import com.celtic.banking.response.ClientBalanceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    public ClientBalanceResponse listClients() {
        return null;
    }

    public ClientBalanceResponse findClientById(Long id){
        return null;
    }

    public void createClient(ClientRequest clientRequest) {
        Client client = Client
                .builder()
                .firstName(clientRequest.getFirstName())
                .lastName(clientRequest.getLastName())
                .cpf(clientRequest.getCpf())
                .build();

        clientRepository.save(client);
    }

    public void deleteClient(Long id) {
    }

    public ClientBalanceResponse updateClientData(ClientRequest clientRequest) {
        return null;
    }
}
