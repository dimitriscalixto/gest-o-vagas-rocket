package br.com.rocket.gestao_vagas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthCompanyDto {

    private String username;
    private String password;
}
