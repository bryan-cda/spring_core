package com.celtic.banking.mapping;

import com.celtic.banking.domain.Client;
import com.celtic.banking.request.ClientResponse;

import java.util.ArrayList;
import java.util.List;

public class ClientResponseMapper {
    public static ClientResponse mapToClientResponse(Client client){
        return ClientResponse
                .builder()
                .firstName(client.getFirstName())
                .cpf(client.getCpf())
                .id(client.getId())
                .lastName(client.getLastName())
                .build();
    }

    public static List<ClientResponse> mapToClientResponseList(List<Client> clientList){
        List<ClientResponse> clientResponseList = new ArrayList<>();

        clientList.forEach(client -> {
             clientResponseList.add(ClientResponseMapper.mapToClientResponse(client));
        });
        return clientResponseList;
    }
}
