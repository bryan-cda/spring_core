package com.celtic.banking.repository;

import com.celtic.banking.domain.Client;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Repository Tests")
@AutoConfigureTestDatabase
public class ClientRepositoryTest {
    @Autowired
    private ClientRepository clientRepository;

    @Test
    @DisplayName("Creation Client test")
    public void givenNewClient_WhenCreate_ThenSave(){
        Client save = clientRepository.save(createClient());
        assertThat(clientRepository.save(save)).isNotNull();
        assertThat(clientRepository.save(save).getFirstName()).isEqualTo(createClient().getFirstName());
        assertThat(clientRepository.save(save).getLastName()).isEqualTo(createClient().getLastName());
        assertThat(clientRepository.save(save).getCpf()).isEqualTo(createClient().getCpf());
    }

    public Client createClient(){
        return Client
                .builder()
                .firstName("Foo")
                .lastName("Bar")
                .cpf("000.000.000-00")
                .build();

    }
}
