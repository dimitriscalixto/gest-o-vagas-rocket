package br.com.rocket.gestao_vagas.modules.job.controller;

import br.com.rocket.gestao_vagas.modules.job.entity.Job;
import br.com.rocket.gestao_vagas.modules.job.useCases.CreateJobUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping("")
    public Job create(@RequestBody Job job) {
        return this.createJobUseCase.execute(job);
    }
}
