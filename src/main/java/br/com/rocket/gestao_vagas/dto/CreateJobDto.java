package br.com.rocket.gestao_vagas.dto;

import lombok.Data;

@Data
public class CreateJobDto {
    private String description;
    private String benefits;
    private String level;
}
