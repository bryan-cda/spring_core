package br.com.spring.pendency.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CompanyPendencyType {
    private Integer companyId;
    private Integer pendencyTypeId;
    private String companyPendencyTypeId;
    private LocalDate createdAt;
}
