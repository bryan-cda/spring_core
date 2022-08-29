package br.com.spring.pendency.service;

import br.com.spring.pendency.domain.Pendency;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Arrays.asList;

@Service
@AllArgsConstructor
public class PendencyService {
    public List<Pendency> listAll() {
        return asList(new Pendency(1, "miss input date"));
    }
}
