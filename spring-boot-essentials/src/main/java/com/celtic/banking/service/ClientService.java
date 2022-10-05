package com.celtic.banking.service;

import com.celtic.banking.domain.Client;
import com.celtic.banking.mapping.ClientMapper;
import com.celtic.banking.mapping.ClientResponseMapper;
import com.celtic.banking.mapping.ClientsResponseMapper;
import com.celtic.banking.repository.ClientRepository;
import com.celtic.banking.request.ClientRequest;
import com.celtic.banking.request.ClientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;



@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public List<ClientResponse> listClients() {
        return ClientsResponseMapper.INSTANCE.mapToListOfClientResponse(clientRepository.findAll());
    }

    public ClientResponse findClientById(Long id){
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client not found for id."));
        return ClientResponseMapper.INSTANCE.matToClientResponse(client);
    }

    public ClientResponse createClient(Client client) {
        return ClientResponseMapper.INSTANCE.matToClientResponse(clientRepository.save(client));
    }

    public void deleteClient(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client not found for id."));
        clientRepository.deleteById(id);
    }

    public ClientResponse updateClientData(ClientRequest clientRequest) {
        Client client = clientRepository.findById(clientRequest.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client not found."));

        clientRepository.deleteById(client.getId());

        return createClient(ClientMapper.INSTANCE.mapToClient(clientRequest));
    }
}
