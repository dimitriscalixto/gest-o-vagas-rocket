package br.com.rocket.gestao_vagas.modules.job;

import br.com.rocket.gestao_vagas.modules.job.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobRepository  extends JpaRepository<Job, UUID> {

}
