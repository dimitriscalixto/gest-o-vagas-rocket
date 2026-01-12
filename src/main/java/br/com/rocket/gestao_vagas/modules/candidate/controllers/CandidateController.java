package br.com.rocket.gestao_vagas.modules.candidate.controllers;

import br.com.rocket.gestao_vagas.exceptions.UserFoundException;
import br.com.rocket.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.rocket.gestao_vagas.modules.candidate.entity.CandidateEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateRepository candidateRepository;

    @PostMapping("/")
    public CandidateEntity create(@Valid @RequestBody CandidateEntity candidate) {
        this.candidateRepository
                .findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });

        return this.candidateRepository.save(candidate);
    }
}
