package br.com.spring.meetapp.converter;

import br.com.spring.meetapp.model.Person;
import br.com.spring.meetapp.vo.request.PersonVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class DozerConverterTest {

    private PersonVO vo;

    private List<PersonVO> personVOS;

    @BeforeEach
    public void setup(){
        this.vo = PersonVO.builder()
                .firstName("Foo")
                .lastName("Bar")
                .key(1L)
                .gender("M")
                .address("Green Street")
                .build();

        PersonVO personOne = PersonVO.builder()
                .firstName("John")
                .lastName("Due")
                .gender("M")
                .address("Blue street")
                .build();

        PersonVO personTwo = PersonVO.builder()
                .firstName("Cora")
                .lastName("Delta")
                .gender("F")
                .address("Orange street")
                .build();

        this.personVOS = new ArrayList<>();
        this.personVOS.addAll(asList(personOne, personTwo));
    }

    @Test
    public void whenConvertPersonVoToPerson_thenReturnPerson(){
        Person person = DozerConverter.parseObject(vo, Person.class);
        assertThat(person.getFirstName()).isEqualTo(vo.getFirstName());
        assertThat(person.getLastName()).isEqualTo(vo.getLastName());
        assertThat(person.getGender()).isEqualTo(vo.getGender());
        assertThat(person.getAddress()).isEqualTo(vo.getAddress());
    }

    @Test
    public void whenConvertListOfPersonVoToListOfPerson_thenReturnListOfPerson(){
        List<Person> peoples = DozerConverter.parseListObjects(personVOS, Person.class);
        assertThat(peoples.size()).isEqualTo(2);
        assertThat(peoples.get(0).getFirstName()).isEqualTo("John");
        assertThat(peoples.get(0).getLastName()).isEqualTo("Due");
        assertThat(peoples.get(0).getGender()).isEqualTo("M");
        assertThat(peoples.get(0).getAddress()).isEqualTo("Blue street");
        assertThat(peoples.get(1).getFirstName()).isEqualTo("Cora");
        assertThat(peoples.get(1).getLastName()).isEqualTo("Delta");
        assertThat(peoples.get(1).getGender()).isEqualTo("F");
        assertThat(peoples.get(1).getAddress()).isEqualTo("Orange street");
    }
}
