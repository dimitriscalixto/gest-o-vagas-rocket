package br.com.rocket.gestao_vagas.modules.candidate.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "candidate")
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Schema(example = "Dimitris Carvalho Calixto")
    private String name;

    @Pattern(regexp = "\\S+", message = "O campo (username) não deve conter espaços")
    @Schema(example = "dimicrf")
    private String username;

    @Email(message = "O campo (email) deve conter um e-mail válido")
    @Schema(example = "dimi@example.com")
    private String email;

    @Length(min = 10, max = 100, message = "A senha deve conter no mínimo 10 caracteres")
    private String password;
    @Schema(example = "Desenvolvedor Java")
    private String description;
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;
}