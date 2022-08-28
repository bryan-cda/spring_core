package br.com.spring.pendency.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PendencyType {
    private Integer id;
    private String type2;
    private LocalDate createdAt;
}
