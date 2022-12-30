package com.celtic.banking.controller;

import com.celtic.banking.request.ClientResponse;
import com.celtic.banking.service.ClientService;
import com.celtic.banking.util.ClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Slf4j
class ClientControllerTest {

    @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientService clientService;

    @BeforeEach
    private void setup(){
        when(clientService.listClients(ArgumentMatchers.any())).thenReturn(ClientUtil.createClientResponsePageable());

        when(clientService.listClients()).thenReturn(ClientUtil.createClientResponseList());

        when(clientService.findClientById(ArgumentMatchers.anyLong())).thenReturn(ClientUtil.createClientResponse());

        when(clientService.createClient(ArgumentMatchers.any())).thenReturn(ClientUtil.createClientResponse());

        when(clientService.updateClientData(ArgumentMatchers.any())).thenReturn(ClientUtil.createClientUpdatedResponse());

        doNothing().when(clientService).deleteClient(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("List all pageable client controller test")
    void givenCallToListClientEndpoint_whenTryToListPageWithAllClients_thenReturn() {
        ResponseEntity<Page<ClientResponse>> clientsPage = clientController.listClients(null);

        assertThat(clientsPage.getBody())
                .doesNotContainNull()
                .doesNotHaveDuplicates()
                .hasSize(2)
                .isNotEmpty()
                .isNotNull();

        assertThat(clientsPage.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(clientsPage.getBody().toList()).isNotEmpty();
        assertThat(clientsPage.getBody().toList().get(0).getFirstName()).isEqualTo("Foo");
        assertThat(clientsPage.getBody().toList().get(0).getLastName()).isEqualTo("Bar");
        assertThat(clientsPage.getBody().toList().get(0).getCpf()).isEqualTo("111.111.111-11");
        assertThat(clientsPage.getBody().toList().get(0).getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("List all client controller test")
    void givenCallToListClientEndpoint_whenTryToListWithAllClients_thenReturn() {
        assertThat(clientController.listClients()).isNotNull();
        assertThat(clientController.listClients().getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(clientController.listClients().getBody()).isNotEmpty();
        assertThat(clientController.listClients().getBody().get(0).getFirstName()).isEqualTo("Foo");
        assertThat(clientController.listClients().getBody().get(0).getLastName()).isEqualTo("Bar");
        assertThat(clientController.listClients().getBody().get(0).getCpf()).isEqualTo("111.111.111-11");
        assertThat(clientController.listClients().getBody().get(0).getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("Find client by id controller test")
    void givenCallToFindClientByIdEndpoint_whenTryToFindClient_thenReturn() {
        ResponseEntity<ClientResponse> clientById = clientController.findClientById(1L);
        assertThat(clientById).isNotNull();
        assertThat(clientById).isEqualTo(HttpStatus.OK);
        assertThat(clientById.getBody().getFirstName()).isEqualTo("Foo");
        assertThat(clientById.getBody().getLastName()).isEqualTo("Bar");
        assertThat(clientById.getBody().getCpf()).isEqualTo("000.000.000-00");
        assertThat(clientById.getBody().getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("Create client controller test")
    void givenCallToCreateClientEndpoint_whenTryCreateClient_thenCreateClient() {
        ResponseEntity<ClientResponse> client = clientController.createClient(null);
        assertThat(client).isNotNull();
        assertThat(client.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(client.getBody().getFirstName()).isEqualTo("Foo");
        assertThat(client.getBody().getLastName()).isEqualTo("Bar");
        assertThat(client.getBody().getCpf()).isEqualTo("000.000.000-00");
        assertThat(client.getBody().getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("Delete client controller test")
    void givenCallToDeleteClientEndpoint_whenTryToDelete_thenUpdateAndReturn() {
        ResponseEntity responseEntity = clientController.deleteClient(1L);
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("Update client controller test")

    void givenCallToUpdateClientEndpoint_whenTryToUpdateClient_thenUpdateAndReturn() {
        assertThat(clientController.createClient(null)).isNotNull();
        assertThat(clientController.createClient(null).getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(clientController.createClient(null).getBody().getFirstName()).isEqualTo("Foo");
        assertThat(clientController.createClient(null).getBody().getLastName()).isEqualTo("Bar");
        assertThat(clientController.createClient(null).getBody().getCpf()).isEqualTo("000.000.000-00");
        assertThat(clientController.createClient(null).getBody().getId()).isEqualTo(1L);

        assertThat(clientController.updateClientData(null).getBody().getId()).isEqualTo(1L);
        assertThat(clientController.updateClientData(null).getBody().getFirstName()).isEqualTo("Foo");
        assertThat(clientController.updateClientData(null).getBody().getLastName()).isEqualTo("Bar");
        assertThat(clientController.updateClientData(null).getBody().getCpf()).isEqualTo("111.111.111-11");

        Assertions.assertThatCode(() -> clientController.updateClientData(null))
                .doesNotThrowAnyException();

        assertThat(clientController.updateClientData(null).getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}