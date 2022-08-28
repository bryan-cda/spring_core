package br.com.spring.pendency.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CompanyPendency {
    private Integer id;
    private Integer pendencyId;
    private String companyPendencyJson;
    private LocalDate createdAt;
}
