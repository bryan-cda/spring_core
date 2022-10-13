package com.celtic.banking.repository;

import com.celtic.banking.domain.Client;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Repository Tests")
@AutoConfigureTestDatabase
@Slf4j
public class ClientRepositoryTest {
    @Autowired
    private ClientRepository clientRepository;

    @Test
    @DisplayName("Find Client by id test")
    public void givenAClientId_whenFindById_thenReturnClient(){
        Client savedClient = clientRepository.save(createClient());

        Client clientFoundById = clientRepository.findById(savedClient.getId())
                .orElseThrow(
                        () -> new RuntimeException("No value present.")
                );

        assertThat(clientFoundById).isNotNull();

        assertThat(clientFoundById.getFirstName()).isEqualTo(savedClient.getFirstName());

        assertThat(clientFoundById.getLastName()).isEqualTo(savedClient.getLastName());

        assertThat(clientFoundById.getCpf()).isEqualTo(savedClient.getCpf());
    }

    @Test
    @DisplayName("Creation Client test")
    public void givenNewClient_WhenCreate_ThenSave(){
        Client save = clientRepository.save(createClient());

        assertThat(clientRepository.save(save)).isNotNull();

        assertThat(clientRepository.save(save).getFirstName()).isEqualTo(createClient().getFirstName());

        assertThat(clientRepository.save(save).getLastName()).isEqualTo(createClient().getLastName());

        assertThat(clientRepository.save(save).getCpf()).isEqualTo(createClient().getCpf());
    }

    @Test
    @DisplayName("Update Client test")
    public void givenExistingClient_whenUpdateData_thenUpdateClient(){
        Client savedClient = clientRepository.save(createClient());

        assertThat(clientRepository.save(savedClient)).isNotNull();

        assertThat(clientRepository.save(savedClient).getFirstName()).isEqualTo(createClient().getFirstName());

        assertThat(clientRepository.save(savedClient).getLastName()).isEqualTo(createClient().getLastName());

        assertThat(clientRepository.save(savedClient).getCpf()).isEqualTo(createClient().getCpf());


        savedClient.setCpf("111.111.111-11");
        savedClient.setFirstName("Bar");
        savedClient.setLastName("Foo");

        Client updatedClient = clientRepository.save(savedClient);

        assertThat(updatedClient.getFirstName()).isNotEqualTo(createClient().getFirstName());

        assertThat(updatedClient.getLastName()).isNotEqualTo(createClient().getLastName());

        assertThat(updatedClient.getCpf()).isNotEqualTo(createClient().getCpf());
    }

    @Test
    @DisplayName("Update Client test")
    public void givenClients_whenFindAllClients_thenReturn(){
        List<Client> findAllClients = clientRepository.saveAll(createClientList());

        assertThat(findAllClients).isNotNull();
        assertThat(findAllClients).size().isEqualTo(2);
    }

    public Client createClient(){
        return Client
                .builder()
                .id(1L)
                .firstName("Foo")
                .lastName("Bar")
                .cpf("000.000.000-00")
                .build();
    }

    public List<Client> createClientList(){
        return List.of(
                new Client(1L, "Foo", "Bar", "111.111.111-11"),
                new Client(2L, "John", "Due", "000.000.000-00")
        );
    }

    @Test
    @DisplayName("Delete Client test")
    public void givenAClientId_thenDeleteClientById_thenDelete(){
        Client save = clientRepository.save(createClient());

        Long idClient = save.getId();

        assertThat(clientRepository.save(save)).isNotNull();

        assertThat(clientRepository.save(save).getFirstName()).isEqualTo(createClient().getFirstName());

        assertThat(clientRepository.save(save).getLastName()).isEqualTo(createClient().getLastName());

        assertThat(clientRepository.save(save).getCpf()).isEqualTo(createClient().getCpf());

        clientRepository.deleteById(save.getId());

        assertThat(clientRepository.findById(idClient)).isEqualTo(Optional.empty());
    }
}
