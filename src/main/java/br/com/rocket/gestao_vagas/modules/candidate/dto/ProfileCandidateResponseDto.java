package br.com.rocket.gestao_vagas.modules.candidate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileCandidateResponseDto {
    @Schema(example = "Desevolvedor Java")
    private String description;
    @Schema(example = "dimitrisc47")
    private String username;
    @Schema(example = "dimitris@example.com")
    private String email;
    private UUID id;
    @Schema(example = "Dimitris")
    private String name;
}
