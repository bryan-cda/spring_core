package com.celtic.banking.controller;

import com.celtic.banking.domain.Client;
import com.celtic.banking.request.ClientResponse;
import com.celtic.banking.service.ClientService;
import com.celtic.banking.util.ClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
@ExtendWith(SpringExtension.class)
@Slf4j
class ClientControllerTest {

    @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientService clientService;

    @BeforeEach
    private void setup(){
        PageImpl<ClientResponse> listAllPageable = new PageImpl<>(ClientUtil.createClientResponseList());
        BDDMockito.when(clientService.listClients(ArgumentMatchers.any())).thenReturn(listAllPageable);
        BDDMockito.when(clientService.listClients()).thenReturn(ClientUtil.createClientResponseList());
        BDDMockito.when(clientService.findClientById(1L)).thenReturn(ClientUtil.createClientResponse());
        BDDMockito.when(clientService.createClient(ArgumentMatchers.any())).thenReturn(ClientUtil.createClientResponse());
    }

    @Test
    @DisplayName("List all pageable client controller test")
    void givenCallToListClientEndpoint_whenTryToListPageWithAllClients_thenReturn() {
        Assertions.assertThat(clientController.listClients(null)).isNotNull();
        Assertions.assertThat(clientController.listClients(null).getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(clientController.listClients(null).getBody().toList()).isNotEmpty();
        Assertions.assertThat(clientController.listClients(null).getBody().toList().get(0).getFirstName()).isEqualTo("Foo");
        Assertions.assertThat(clientController.listClients(null).getBody().toList().get(0).getLastName()).isEqualTo("Bar");
        Assertions.assertThat(clientController.listClients(null).getBody().toList().get(0).getCpf()).isEqualTo("111.111.111-11");
        Assertions.assertThat(clientController.listClients(null).getBody().toList().get(0).getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("List all client controller test")
    void givenCallToListClientEndpoint_whenTryToListWithAllClients_thenReturn() {
        Assertions.assertThat(clientController.listClients()).isNotNull();
        Assertions.assertThat(clientController.listClients().getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(clientController.listClients().getBody()).isNotEmpty();
        Assertions.assertThat(clientController.listClients().getBody().get(0).getFirstName()).isEqualTo("Foo");
        Assertions.assertThat(clientController.listClients().getBody().get(0).getLastName()).isEqualTo("Bar");
        Assertions.assertThat(clientController.listClients().getBody().get(0).getCpf()).isEqualTo("111.111.111-11");
        Assertions.assertThat(clientController.listClients().getBody().get(0).getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("Find client by id controller test")
    void givenCallToFindClientByIdEndpoint_whenTryToFindClient_thenReturn() {
        Assertions.assertThat(clientController.findClientById(1L)).isNotNull();
        Assertions.assertThat(clientController.findClientById(1L).getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(clientController.findClientById(1L).getBody().getFirstName()).isEqualTo("Foo");
        Assertions.assertThat(clientController.findClientById(1L).getBody().getLastName()).isEqualTo("Bar");
        Assertions.assertThat(clientController.findClientById(1L).getBody().getCpf()).isEqualTo("000.000.000-00");
        Assertions.assertThat(clientController.findClientById(1L).getBody().getId()).isEqualTo(1L);
    }

    @Test
    void givenCallToCreateClientEndpoint_whenTryCreateClient_thenCreateClient() {
        Assertions.assertThat(clientController.createClient(null)).isNotNull();
        Assertions.assertThat(clientController.createClient(null).getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(clientController.createClient(null).getBody().getFirstName()).isEqualTo("Foo");
        Assertions.assertThat(clientController.createClient(null).getBody().getLastName()).isEqualTo("Bar");
        Assertions.assertThat(clientController.createClient(null).getBody().getCpf()).isEqualTo("000.000.000-00");
        Assertions.assertThat(clientController.createClient(null).getBody().getId()).isEqualTo(1L);
    }

    @Test
    void deleteClient() {
    }

    @Test
    void updateClientData() {
    }
}