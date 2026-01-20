package br.com.rocket.gestao_vagas.modules.candidate.useCases;

import br.com.rocket.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.rocket.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDto;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service

public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDto execute(UUID candidateid) {
        var candidate = candidateRepository
                .findById(candidateid)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("User not found");
                });
        var candidateDTO = ProfileCandidateResponseDto.builder()
                .description(candidate.getDescription())
                .username(candidate.getUsername())
                .email(candidate.getEmail())
                .id(candidate.getId())
                .name(candidate.getName())
                .build();
        return candidateDTO;
    }
}
