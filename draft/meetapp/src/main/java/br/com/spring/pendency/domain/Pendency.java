package br.com.spring.pendency.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Pendency {
    private Integer pendencyId;
    private Integer pendencyTypeId;
    private String uuid;
    private String status;
    private String description;
    private LocalDate createdAt;
    private LocalDate modifiedAt;
}
