package br.com.rocket.gestao_vagas.modules.job.controller;

import br.com.rocket.gestao_vagas.dto.CreateJobDto;
import br.com.rocket.gestao_vagas.modules.job.entity.Job;
import br.com.rocket.gestao_vagas.modules.job.useCases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping("")
    public Job create(@RequestBody CreateJobDto createJobDto, HttpServletRequest request) {
        var companyId = request.getAttribute("company_id");

        var job = Job.builder()
         .benefits(createJobDto.getBenefits())
         .companyId(UUID.fromString(companyId.toString()))
         .description(createJobDto.getDescription())
         .level(createJobDto.getLevel())
         .build();

        return this.createJobUseCase.execute(job);
    }
}
