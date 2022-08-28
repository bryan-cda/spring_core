package br.com.spring.pendency.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CompanyPendencyAnswer {
    private Integer pendencyAnswerId;
    private String companyPendencyAnswerId;
    private String companyPendencyAnswerJson;
    private LocalDate createdAt;
}
