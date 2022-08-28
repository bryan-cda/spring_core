package br.com.spring.pendency.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PendencyAnswer {
    private Integer pendencyId;
    private String uuid;
    private String answer;
    private Boolean answerApproved;
    private LocalDate createdAt;
}
