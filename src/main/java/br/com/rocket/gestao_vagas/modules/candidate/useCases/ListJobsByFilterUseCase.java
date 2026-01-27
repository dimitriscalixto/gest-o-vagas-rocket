package br.com.rocket.gestao_vagas.modules.candidate.useCases;

import br.com.rocket.gestao_vagas.modules.job.JobRepository;
import br.com.rocket.gestao_vagas.modules.job.entity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListJobsByFilterUseCase {

    @Autowired
    private JobRepository jobRepository;

    public List<Job> execute(String filter) {
        return this.jobRepository.findByDescriptionContainingIgnoreCase(filter);
    }
}
