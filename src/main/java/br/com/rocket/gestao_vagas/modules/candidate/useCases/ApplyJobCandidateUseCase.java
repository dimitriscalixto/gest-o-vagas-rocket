package br.com.rocket.gestao_vagas.modules.candidate.useCases;

import br.com.rocket.gestao_vagas.exceptions.JobNotFoundException;
import br.com.rocket.gestao_vagas.exceptions.UserNotFoundException;
import br.com.rocket.gestao_vagas.modules.candidate.ApplyJobRepository;
import br.com.rocket.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.rocket.gestao_vagas.modules.candidate.entity.ApplyJobEntity;
import br.com.rocket.gestao_vagas.modules.job.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplyJobCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;

    public ApplyJobEntity execute(UUID idCandidate, UUID idJob) {
        this.candidateRepository.findById(idCandidate)
        .orElseThrow(() -> new UserNotFoundException());

        this.jobRepository.findById(idJob)
        .orElseThrow(() -> new JobNotFoundException());

        var applyJob = ApplyJobEntity.builder()
        .candidateId(idCandidate)
        .jobId(idJob)
        .build();

        return applyJobRepository.save(applyJob);
    }
}
