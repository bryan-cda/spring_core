package br.com.spring.pendency.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pendency {
    private Integer code;
    private String description;
}
