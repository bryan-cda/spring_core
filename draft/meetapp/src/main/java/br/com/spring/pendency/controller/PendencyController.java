package br.com.spring.pendency.controller;

import br.com.spring.pendency.domain.Pendency;
import br.com.spring.pendency.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/pendency")
@Slf4j
@RequiredArgsConstructor
public class PendencyController {
    private final DateUtil dateUtil;

    @GetMapping
    public List<Pendency> list(){
        log.info(dateUtil.getCurrentLocalDateTime(LocalDateTime.now()));

        return List.of(new Pendency(1, 1, "2772", "Urgency", "Report Problem", LocalDate.now(),LocalDate.now()));
    }
}
