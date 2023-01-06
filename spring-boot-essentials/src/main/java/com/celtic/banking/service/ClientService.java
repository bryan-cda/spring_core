package com.celtic.banking.service;

import com.celtic.banking.domain.Client;
import com.celtic.banking.mapping.ClientRequestMapper;
import com.celtic.banking.mapping.ClientResponseMapper;
import com.celtic.banking.repository.ClientRepository;
import com.celtic.banking.request.ClientRequest;
import com.celtic.banking.request.ClientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public List<ClientResponse> listClients() {
        return ClientResponseMapper.mapToClientResponseList(clientRepository.findAll());
    }

    public Page<ClientResponse> listClients(Pageable page) {
        return new PageImpl<>(ClientResponseMapper.mapToClientResponseList(clientRepository.findAll()));
    }

    public Client findClientById(Long id){
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client not found for id."));
        return client;
    }

    public ClientResponse createClient(ClientRequest clientRequest) {
        return ClientResponseMapper.mapToClientResponse(clientRepository.save(ClientRequestMapper.mapToClient(clientRequest)));
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(findClientById(id).getId());
    }

    public ClientResponse updateClientData(ClientRequest clientRequest) {
        Client client = findClientById(clientRequest.getId());

        Client updatedClient = findClientAndUpdateData(client, clientRequest);

        clientRepository.save(updatedClient);

        return ClientResponseMapper.mapToClientResponse(updatedClient);


    }

    public Client findClientAndUpdateData(Client client, ClientRequest clientRequest){
        client.setCpf(clientRequest.getCpf());
        client.setFirstName(clientRequest.getFirstName());
        client.setLastName(clientRequest.getLastName());
        return client;
    }


    public ClientResponse findByFirstName(String name) {
        return ClientResponseMapper.mapToClientResponse(clientRepository.findByFirstName(name));
    }
}
