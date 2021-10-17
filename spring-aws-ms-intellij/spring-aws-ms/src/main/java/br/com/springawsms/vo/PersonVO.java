package br.com.springawsms.vo;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonPropertyOrder({"id", "firstName", "lastName", "gender", "address"})
public class PersonVO {
    private Long id;

    private String firstName;

    private String lastName;

    private String address;

    private String gender;
}

