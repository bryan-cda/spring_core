package com.celtic.banking.service;

import com.celtic.banking.domain.Client;
import com.celtic.banking.mapping.ClientRequestMapper;
import com.celtic.banking.repository.ClientRepository;
import com.celtic.banking.request.ClientRequest;
import com.celtic.banking.request.ClientResponse;
import com.celtic.banking.util.ClientUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @BeforeEach
    public void setup(){
        when(clientRepository.findAll()).thenReturn(ClientUtil.createClientList());
        when(clientRepository.findAll(ArgumentMatchers.any(PageRequest.class))).thenReturn(new PageImpl<>((ClientUtil.createClientList())));
        when(clientRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(ClientUtil.createClient()));
        PageImpl<Client> clientPage = new PageImpl<>(List.of(ClientUtil.createClient()));
        when(clientRepository.findAll(ArgumentMatchers.any(PageRequest.class))).thenReturn(clientPage);
        when(clientRepository.save(ClientUtil.createClientOnDatabase())).thenReturn(ClientUtil.createClient());
        doNothing().when(clientRepository).delete(ArgumentMatchers.any());
        when(clientRepository.findById(1L)).thenReturn(Optional.of(ClientUtil.createClient()));
        when(clientRepository.findById(2L)).thenReturn(Optional.empty());
        when(clientRepository.findByFirstName("Foo")).thenReturn(ClientUtil.createClient());
    }

    @Test
    @DisplayName("List all clients service test")
    void givenGenericService_whenListAllClients_thenReturn() {
        List<ClientResponse> listClients = clientService.listClients();
        assertThat(listClients).isNotEmpty();
        assertThat(listClients).isNotNull();
        assertThat(listClients).size().isEqualTo(2);
    }

    @Test
    @DisplayName("Find client by id service test")
    void givenGenericService_whenFindClientById_thenReturn() {
        Client clientById = clientService.findClientById(1L);
        assertThat(clientById).isNotNull();
    }

    @Test
    @DisplayName("List all clients with pagination service test")
    void givenGenericService_whenListAllClientsWithPagination_thenReturn() {
        assertThat(clientService.listClients(PageRequest.of(1,1))).isNotNull();
    }

    @Test
    @DisplayName("Create client service test")
    void givenGenericService_whenTryToSaveClient_thenReturn() {
        assertThat(clientService.createClient(ClientRequestMapper.mapToClientRequest(ClientUtil.createClient()))).isNotNull();
    }

    @Test
    @DisplayName("Delete client service test")
    void givenGenericService_whenTryToDeleteClient_thenExecute() {
        assertThatCode(() -> clientService.deleteClient(1L))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Update client service test")
    void givenGenericService_whenTryUpdateClient_thenReturn() {
        assertThat(clientService.updateClientData(ClientUtil.createUpdateClientRequest())).isNotNull();
    }

    @Test
    @DisplayName("Throw exception when find client that don't exist by id test")
    void givenGenericService_whenTryToFindAClientById_thenThrowException() {
        Assertions.assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> clientService.findClientById(2L));
    }

    @Test
    @DisplayName("Find client by firstName service test")
    void givenGenericService_whenTryToFindAClientByFistName_thenReturn() {
        assertThat(clientService.findByFirstName("Foo")).isNotNull();
    }
}