package com.celtic.banking.util;

import com.celtic.banking.domain.Client;
import com.celtic.banking.request.ClientRequest;
import com.celtic.banking.request.ClientResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public class ClientUtil {
    public static Client createClient(){
        return Client
                .builder()
                .id(1L)
                .firstName("Foo")
                .lastName("Bar")
                .cpf("000.000.000-00")
                .build();
    }

    public static Client createClientOnDatabase(){
        return Client
                .builder()
                .firstName("Foo")
                .lastName("Bar")
                .cpf("000.000.000-00")
                .build();
    }

    public static ClientResponse createClientResponse(){
        return ClientResponse
                .builder()
                .id(1L)
                .firstName("Foo")
                .lastName("Bar")
                .cpf("000.000.000-00")
                .build();
    }

    public static ClientRequest createClientRequest(){
        return ClientRequest
                .builder()
                .id(1L)
                .firstName("Foo")
                .lastName("Bar")
                .cpf("000.000.000-00")
                .build();
    }

    public static ClientResponse createClientUpdatedResponse(){
        return ClientResponse
                .builder()
                .id(1L)
                .firstName("Foo")
                .lastName("Bar")
                .cpf("111.111.111-11")
                .build();
    }

    public static ClientRequest createUpdateClientRequest(){
        return ClientRequest
                .builder()
                .id(1L)
                .firstName("Other First name")
                .firstName("Other Last name")
                .cpf("111.111.111-11")
                .build();
    }

    public static List<Client> createClientList(){
        return List.of(
                new Client(1L, "Foo", "Bar", "111.111.111-11"),
                new Client(2L, "John", "Due", "000.000.000-00")
        );
    }

    public static List<ClientResponse> createClientResponseList(){
        return List.of(
                new ClientResponse(1L, "Foo", "Bar", "111.111.111-11"),
                new ClientResponse(2L, "John", "Due", "000.000.000-00")
        );
    }

    public static PageImpl<ClientResponse> createClientResponsePageable(){
        return new PageImpl<>(ClientUtil.createClientResponseList());
    }

    public static PageImpl<Client> createClientPage(){
        return new PageImpl<>(ClientUtil.createClientList());
    }
}
