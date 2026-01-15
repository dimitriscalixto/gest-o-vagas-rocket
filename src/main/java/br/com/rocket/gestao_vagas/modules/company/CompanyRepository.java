package br.com.rocket.gestao_vagas.modules.company;

import br.com.rocket.gestao_vagas.modules.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
    Optional<Company> findByUsernameOrEmail(String username, String email);

    Optional<Company> findByUsername(String username);
}
