package com.celtic.banking.repository;

import com.celtic.banking.domain.Client;
import com.celtic.banking.util.ClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        Client savedClient = clientRepository.save(ClientUtil.createClient());

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
        Client save = clientRepository.save(ClientUtil.createClient());

        assertThat(clientRepository.save(save)).isNotNull();

        assertThat(clientRepository.save(save).getFirstName()).isEqualTo(ClientUtil.createClient().getFirstName());

        assertThat(clientRepository.save(save).getLastName()).isEqualTo(ClientUtil.createClient().getLastName());

        assertThat(clientRepository.save(save).getCpf()).isEqualTo(ClientUtil.createClient().getCpf());
    }

    @Test
    @DisplayName("Update Client test")
    public void givenExistingClient_whenUpdateData_thenUpdateClient(){
        Client savedClient = clientRepository.save(ClientUtil.createClient());

        assertThat(clientRepository.save(savedClient)).isNotNull();

        assertThat(clientRepository.save(savedClient).getFirstName()).isEqualTo(ClientUtil.createClient().getFirstName());

        assertThat(clientRepository.save(savedClient).getLastName()).isEqualTo(ClientUtil.createClient().getLastName());

        assertThat(clientRepository.save(savedClient).getCpf()).isEqualTo(ClientUtil.createClient().getCpf());


        savedClient.setCpf("111.111.111-11");
        savedClient.setFirstName("Bar");
        savedClient.setLastName("Foo");

        Client updatedClient = clientRepository.save(savedClient);

        assertThat(updatedClient.getFirstName()).isNotEqualTo(ClientUtil.createClient().getFirstName());

        assertThat(updatedClient.getLastName()).isNotEqualTo(ClientUtil.createClient().getLastName());

        assertThat(updatedClient.getCpf()).isNotEqualTo(ClientUtil.createClient().getCpf());
    }

    @Test
    @DisplayName("Find all Clients test")
    public void givenClients_whenFindAllClients_thenReturn(){
        List<Client> findAllClients = clientRepository.saveAll(ClientUtil.createClientList());

        assertThat(findAllClients).isNotNull();
        assertThat(findAllClients).size().isEqualTo(2);
    }

    @Test
    @DisplayName("Find Client by first name test")
    public void givenAName_whenFindClientByName_thenReturn(){
        clientRepository.saveAll(ClientUtil.createClientList());

        Client foo = clientRepository.findByFirstName("Foo");

        assertThat(foo).isNotNull();

        assertThat(foo.getFirstName()).isEqualTo("Foo");
    }

    @Test
    @DisplayName("Delete Client test")
    public void givenAClientId_thenDeleteClientById_thenDelete(){
        Client save = clientRepository.save(ClientUtil.createClient());

        Long idClient = save.getId();

        assertThat(clientRepository.save(save)).isNotNull();

        assertThat(clientRepository.save(save).getFirstName()).isEqualTo(ClientUtil.createClient().getFirstName());

        assertThat(clientRepository.save(save).getLastName()).isEqualTo(ClientUtil.createClient().getLastName());

        assertThat(clientRepository.save(save).getCpf()).isEqualTo(ClientUtil.createClient().getCpf());

        clientRepository.deleteById(save.getId());

        assertThat(clientRepository.findById(idClient)).isEqualTo(Optional.empty());
    }
    @Test
    @DisplayName("Throw ConstraintViolationException when save Client without first name test")
    public void givenAClientWithEmptyFirstName_whenSaveClient_thenThrowException(){
        Client client = Client.builder().lastName("bar").cpf("000.000.000-00").build();
        assertThatThrownBy(() -> this.clientRepository.save(client)).isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    @DisplayName("Throw ConstraintViolationException when save Client without last name test")
    public void givenAClientWithEmptyLastName_whenSaveClient_thenThrowException(){
        Client client = Client.builder().lastName("bar").cpf("000.000.000-00").build();
        Assertions.assertThrows(ConstraintViolationException.class, () ->{
            clientRepository.save(client);
        });
    }
    @Test
    @DisplayName("Throw ConstraintViolationException when save Client without CPF test")
    public void givenAClientWithEmptyCPF_whenSaveClient_thenThrowException(){
        Client client = Client.builder().firstName("foo").lastName("bar").build();
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            clientRepository.save(client);
        });
    }
}
