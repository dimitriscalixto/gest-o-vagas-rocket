package br.com.rocket.gestao_vagas.modules.company.controller;

import br.com.rocket.gestao_vagas.exceptions.UserFoundException;
import br.com.rocket.gestao_vagas.modules.company.entity.Company;
import br.com.rocket.gestao_vagas.modules.company.useCases.CreateCompanyUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CreateCompanyUseCase createCompanyUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@RequestBody Company company) {
        try{
           var result = this.createCompanyUseCase.execute(company);
           return ResponseEntity.ok().body(result);
        } catch (UserFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
