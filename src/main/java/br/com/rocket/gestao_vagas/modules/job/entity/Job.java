package br.com.rocket.gestao_vagas.modules.job.entity;

import br.com.rocket.gestao_vagas.modules.company.entity.Company;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "Job")
@Data
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String description;
    private String benefits;
    private String level;

    @ManyToOne()
    @JoinColumn(name = "company_id",insertable=false,updatable=false)
    private Company company;

    @Column(name = "company_id")
    private UUID companyId;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
