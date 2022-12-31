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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Arrays.asList;
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
        when(clientService.findByFirstName(ArgumentMatchers.anyString())).thenReturn(ClientUtil.createClientResponse());
    }

    @Test
    @DisplayName("List all pageable client controller test")
    void givenCallToListClientEndpoint_whenTryToListPageWithAllClients_thenReturn() {
        ResponseEntity<Page<ClientResponse>> pageOfClients = clientController.listClients(null);

        assertThat(pageOfClients.getBody())
                .doesNotContainNull()
                .doesNotHaveDuplicates()
                .hasSize(2)
                .isNotEmpty()
                .isNotNull();

        ClientResponse clientResponse = pageOfClients.getBody().toList().get(0);

        assertThat(pageOfClients.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(pageOfClients.getBody().toList()).isNotEmpty();
        assertThat(clientResponse.getFirstName()).isEqualTo("Foo");
        assertThat(clientResponse.getLastName()).isEqualTo("Bar");
        assertThat(clientResponse.getCpf()).isEqualTo("111.111.111-11");
        assertThat(clientResponse.getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("List all client controller test")
    void givenCallToListClientEndpoint_whenTryToListWithAllClients_thenReturn() {
        ResponseEntity<List<ClientResponse>> responseEntityOfClientResponse = clientController.listClients();
        assertThat(responseEntityOfClientResponse).isNotNull();
        assertThat(responseEntityOfClientResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntityOfClientResponse.getBody()).isNotEmpty();
        assertThat(responseEntityOfClientResponse.getBody().get(0).getFirstName()).isEqualTo("Foo");
        assertThat(responseEntityOfClientResponse.getBody().get(0).getLastName()).isEqualTo("Bar");
        assertThat(responseEntityOfClientResponse.getBody().get(0).getCpf()).isEqualTo("111.111.111-11");
        assertThat(responseEntityOfClientResponse.getBody().get(0).getId()).isEqualTo(1L);
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
        ResponseEntity<ClientResponse> client = clientController.createClient(null);
        assertThat(client).isNotNull();
        assertThat(client.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(client.getBody().getFirstName()).isEqualTo("Foo");
        assertThat(client.getBody().getLastName()).isEqualTo("Bar");
        assertThat(client.getBody().getCpf()).isEqualTo("000.000.000-00");
        assertThat(client.getBody().getId()).isEqualTo(1L);

        ResponseEntity<ClientResponse> updatedClientResponseResponseEntity = clientController.updateClientData(null);
        assertThat(updatedClientResponseResponseEntity.getBody().getId()).isEqualTo(1L);
        assertThat(updatedClientResponseResponseEntity.getBody().getFirstName()).isEqualTo("Foo");
        assertThat(updatedClientResponseResponseEntity.getBody().getLastName()).isEqualTo("Bar");
        assertThat(updatedClientResponseResponseEntity.getBody().getCpf()).isEqualTo("111.111.111-11");

        Assertions.assertThatCode(() -> clientController.updateClientData(null))
                .doesNotThrowAnyException();
        assertThat(updatedClientResponseResponseEntity).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("Find client by name controller test")
    void givenGenericName_whenTryToFindClientByName_thenReturn() {
        ClientResponse wanted = clientController.findClientByName("John");
        ClientResponse expected = ClientUtil.createClientResponse();
        assertThat(wanted).isNotNull();
        assertThat(wanted).isEqualTo(expected);
        assertThat(wanted.getCpf()).isEqualTo(expected.getCpf());
        assertThat(wanted.getFirstName()).isEqualTo(expected.getFirstName());
        assertThat(wanted.getLastName()).isEqualTo(expected.getLastName());
    }
}