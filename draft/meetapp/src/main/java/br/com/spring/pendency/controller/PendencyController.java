package br.com.spring.pendency.controller;

import br.com.spring.pendency.domain.Pendency;
import br.com.spring.pendency.service.PendencyService;
import br.com.spring.pendency.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/pendencies")
@Slf4j
@RequiredArgsConstructor
public class PendencyController {
    private final DateUtil dateUtil;
    private final PendencyService pendencyService;

    @GetMapping
    public ResponseEntity<List<Pendency>> listAll(){
        log.info(dateUtil.getCurrentLocalDateTime(LocalDateTime.now()));
        return ResponseEntity.ok(pendencyService.listAll());
    }
}
