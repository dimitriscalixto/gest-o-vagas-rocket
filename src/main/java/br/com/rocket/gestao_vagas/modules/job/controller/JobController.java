package br.com.rocket.gestao_vagas.modules.job.controller;

import br.com.rocket.gestao_vagas.dto.CreateJobDto;
import br.com.rocket.gestao_vagas.modules.job.entity.Job;
import br.com.rocket.gestao_vagas.modules.job.useCases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/company/job")
public class JobController {

    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping("/")
    @PreAuthorize("hasRole('COMPANY')")
    public Job create(@RequestBody CreateJobDto createJobDto, HttpServletRequest request) {
        var companyId = request.getAttribute("company_id");
        System.out.println("companyId: " + companyId);
        var job = Job.builder()
         .benefits(createJobDto.getBenefits())
         .companyId(UUID.fromString(companyId.toString()))
         .description(createJobDto.getDescription())
         .level(createJobDto.getLevel())
         .build();

        return this.createJobUseCase.execute(job);
    }
}
