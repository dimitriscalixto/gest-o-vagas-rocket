package br.com.rocket.gestao_vagas.modules.company.controllers;

import br.com.rocket.gestao_vagas.dto.CreateJobDto;
import br.com.rocket.gestao_vagas.modules.company.CompanyRepository;
import br.com.rocket.gestao_vagas.modules.company.entity.Company;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import utils.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreateJobControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private CompanyRepository companyRepository;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    public void shouldBeAbleToCreateANewJob() throws Exception {

        var company = Company.builder()
                .description("COMPANY_DESCRIPTION")
                .email("email@company.com")
                .password("1234567890")
                .username("COMPANY_USERNAME")
                .name("COMPANY_NAME")
                .build();
        company = companyRepository.saveAndFlush(company);

        CreateJobDto createdJobDto = CreateJobDto.builder()
                .benefits("BENEFITS_TEST")
                .description("DESCRIPTION_TEST")
                .level("LEVEL_TEST")
                .build();

        var result = mvc.perform(MockMvcRequestBuilders.post("/company/job/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.objectToJSON(createdJobDto))
                        .header("Authorization", TestUtils.generateToken(company.getId()))
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
        System.out.println(result);

    }


}
