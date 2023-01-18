package com.celtic.banking.mapping;

import com.celtic.banking.domain.Client;
import com.celtic.banking.request.ClientRequest;
import com.celtic.banking.request.ClientResponse;

public class ClientRequestMapper {
    public static ClientRequest mapToClientRequest(Client client){
        return ClientRequest
                .builder()
                .cpf(client.getCpf())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .build();
    }
    public static ClientRequest mapToClientRequestWithId(Client client){
        return ClientRequest
                .builder()
                .id(client.getId())
                .cpf(client.getCpf())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .build();
    }

    public static Client mapToClient(ClientRequest clientRequest){
        return Client
                .builder()
                .cpf(clientRequest.getCpf())
                .firstName(clientRequest.getFirstName())
                .lastName(clientRequest.getLastName())
                .build();
    }
}
