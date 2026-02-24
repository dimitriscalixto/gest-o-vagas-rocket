package br.com.rocket.gestao_vagas.modules.job.useCases;

import br.com.rocket.gestao_vagas.exceptions.CompanyNotFoundException;
import br.com.rocket.gestao_vagas.modules.company.CompanyRepository;
import br.com.rocket.gestao_vagas.modules.job.JobRepository;
import br.com.rocket.gestao_vagas.modules.job.entity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public Job execute(Job job) {
        companyRepository.findById(job.getCompanyId()).orElseThrow(() -> {
            throw new CompanyNotFoundException();
        });
        return this.jobRepository.save(job);
    }

}
