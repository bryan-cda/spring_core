package br.com.springawsms.vo;

import lombok.*;
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonVO {
    private Long id;

    private String firstName;

    private String lastName;

    private String address;

    private String gender;
}

