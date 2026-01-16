package br.com.rocket.gestao_vagas.modules.job.useCases;

import br.com.rocket.gestao_vagas.dto.CreateJobDto;
import br.com.rocket.gestao_vagas.modules.job.JobRepository;
import br.com.rocket.gestao_vagas.modules.job.entity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository jobRepository;

    public Job execute(Job job) {
        return this.jobRepository.save(job);
    }

}
