package br.com.spring.meetapp.controller;

import br.com.spring.meetapp.domain.Agent;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/agents")
public class AgentController {
    @GetMapping
    public List<Agent> list(){
        return List.of(new Agent("Raze"), new Agent("Neon"), new Agent("Reyna"));
    }
}
