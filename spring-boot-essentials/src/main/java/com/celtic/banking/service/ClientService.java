package com.celtic.banking.service;

import com.celtic.banking.domain.Client;
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

import java.util.ArrayList;
import java.util.List;



@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public List<ClientResponse> listClients() {
        List<Client> clients = clientRepository.findAll();
        List<ClientResponse> clientResponseList = new ArrayList<>();

        clients.forEach(client -> {
            ClientResponse response = ClientResponse.builder()
                    .id(client.getId())
                    .firstName(client.getFirstName())
                    .lastName(client.getLastName())
                    .cpf(client.getCpf())
                    .build();

            clientResponseList.add(response);
        });

        return clientResponseList;
    }

    public Page<ClientResponse> listClients(Pageable page) {
        Page<Client> clients = clientRepository.findAll(page);
        List<ClientResponse> clientResponseList = new ArrayList<>();

        clients.forEach(client -> {
            ClientResponse response = ClientResponse
                    .builder()
                    .id(client.getId())
                    .firstName(client.getFirstName())
                    .lastName(client.getLastName())
                    .cpf(client.getCpf())
                    .build();
            clientResponseList.add(response);
        });
        return new PageImpl<>(clientResponseList);
    }

    public ClientResponse findClientById(Long id){
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client not found for id."));
        return ClientResponseMapper.INSTANCE.mapToClientResponse(client);
    }

    public ClientResponse createClient(ClientRequest clientRequest) {
        Client clientSaved = Client
                .builder()
                .cpf(clientRequest.getCpf())
                .firstName(clientRequest.getFirstName())
                .lastName(clientRequest.getLastName())
                .build();
        Client saved = clientRepository.save(clientSaved);

        return ClientResponse
                .builder()
                .id(saved.getId())
                .firstName(saved.getFirstName())
                .lastName(saved.getLastName())
                .cpf(saved.getCpf())
                .build();
    }

    public void deleteClient(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client not found for id."));
        clientRepository.deleteById(client.getId());
    }

    public ClientResponse updateClientData(ClientRequest clientRequest) {
        Client client = clientRepository.findById(clientRequest.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client not found."));

        client.setCpf(clientRequest.getCpf());
        client.setFirstName(clientRequest.getFirstName());
        client.setLastName(clientRequest.getLastName());

        return ClientResponseMapper.INSTANCE.mapToClientResponse(clientRepository.save(client));
    }


}
