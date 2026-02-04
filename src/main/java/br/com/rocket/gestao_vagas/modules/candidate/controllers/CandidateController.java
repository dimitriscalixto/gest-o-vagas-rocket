package br.com.rocket.gestao_vagas.modules.candidate.controllers;

import br.com.rocket.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDto;
import br.com.rocket.gestao_vagas.modules.candidate.entity.ApplyJobEntity;
import br.com.rocket.gestao_vagas.modules.candidate.entity.CandidateEntity;
import br.com.rocket.gestao_vagas.modules.candidate.useCases.ApplyJobCandidateUseCase;
import br.com.rocket.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import br.com.rocket.gestao_vagas.modules.candidate.useCases.ListJobsByFilterUseCase;
import br.com.rocket.gestao_vagas.modules.candidate.useCases.ProfileCandidateUseCase;
import br.com.rocket.gestao_vagas.modules.job.entity.Job;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Candidato", description = "Informações do candidato")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

    @Autowired
    private ListJobsByFilterUseCase listJobsByFilterUseCase;

    @Autowired
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @PostMapping("/")
    @Operation(
            summary = "Criação do Candidato",
            description = "Endpoint responsável pela criação do candidato"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = CandidateEntity.class))
            }),
            @ApiResponse(responseCode = "400", description = "User already exists")
    })
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
    @Operation(
            summary = "Perfil do Candidato",
            description = "Essa função é responsável por buscara as informações do candidato"
    )
    @SecurityRequirement(name = "jwt_auth")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ProfileCandidateResponseDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "user not found")
    })
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
    @Operation(summary = "Listagem de vagas disponíveis para o candidato", description = "Função responsável por listar todas as vagas disponíveis pelo filtro enviado pelo usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = Job.class)))
            })
    })
    @SecurityRequirement(name = "jwt_auth")
    public List<Job> findJobByFilter(@RequestParam String filter) {
        return this.listJobsByFilterUseCase.execute(filter);
    }

    @PostMapping("/job/apply")
    @PreAuthorize("hasRole('CANDIDATE')")
    @SecurityRequirement(name = "jwt_auth")
    @Operation(summary = "Aplicação em um job existente", description = "Endpoint responsável pela aplicação no job de um candidate")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = ApplyJobEntity.class)))
            })
    })
    public ResponseEntity<Object> applyJob(HttpServletRequest request, @RequestBody UUID jobId) {
        var candidateId = request.getAttribute("candidate_id");
        try {
            var result = this.applyJobCandidateUseCase.execute(UUID.fromString(candidateId.toString()), jobId);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
