package br.com.rocket.gestao_vagas.modules.candidate.controllers;

import br.com.rocket.gestao_vagas.modules.candidate.entity.CandidateEntity;
import br.com.rocket.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import br.com.rocket.gestao_vagas.modules.candidate.useCases.ListJobsByFilterUseCase;
import br.com.rocket.gestao_vagas.modules.candidate.useCases.ProfileCandidateUseCase;
import br.com.rocket.gestao_vagas.modules.job.entity.Job;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

    @Autowired
    private ListJobsByFilterUseCase listJobsByFilterUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidate) {
        try {
            var result = this.createCandidateUseCase.execute(candidate);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Object> findById(HttpServletRequest request) {
        var candidateId = request.getAttribute("candidate_id");
        try {
            var result = this.profileCandidateUseCase.execute(UUID.fromString(candidateId.toString()));
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/job")
    @PreAuthorize("hasRole('CANDIDATE')")
    public List<Job> findJobByFilter(@RequestParam  String filter){
            return this.listJobsByFilterUseCase.execute(filter);
    }
}
