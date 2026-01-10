package br.com.rocket.gestao_vagas.modules.candidate.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class CandidateEntity {

    private UUID id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String description;
    private String curriculum;
}
