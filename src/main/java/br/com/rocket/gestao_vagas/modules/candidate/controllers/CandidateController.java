package br.com.rocket.gestao_vagas.modules.candidate.controllers;

import br.com.rocket.gestao_vagas.exceptions.UserFoundException;
import br.com.rocket.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.rocket.gestao_vagas.modules.candidate.entity.CandidateEntity;
import br.com.rocket.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidate) {
        try {
            var result = this.createCandidateUseCase.execute(candidate);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
