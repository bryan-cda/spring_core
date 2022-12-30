package com.celtic.banking.service;

import com.celtic.banking.repository.ClientRepository;
import com.celtic.banking.util.ClientUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @BeforeEach
    public void setup(){
        BDDMockito.when(clientRepository.findAll()).thenReturn(ClientUtil.createClientList());
        BDDMockito.when(clientRepository.findAll(ArgumentMatchers.any(PageRequest.class))).thenReturn(new PageImpl<>(List.of(ClientUtil.createClient())));
    }

    @Test
    @DisplayName("")
    void listClients() {
        Assertions.assertThat(clientService.listClients()).isNotEmpty();
        Assertions.assertThat(clientService.listClients()).isNotNull();
        Assertions.assertThat(clientService.listClients()).size().isEqualTo(2);
    }

    @Test
    void testListClients() {
    }

    @Test
    void findClientById() {
    }

    @Test
    void createClient() {
    }

    @Test
    void deleteClient() {
    }

    @Test
    void updateClientData() {
    }
}