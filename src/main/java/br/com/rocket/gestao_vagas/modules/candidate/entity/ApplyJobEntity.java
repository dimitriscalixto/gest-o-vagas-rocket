package br.com.rocket.gestao_vagas.modules.candidate.entity;


import br.com.rocket.gestao_vagas.modules.job.entity.Job;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "apply_job")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplyJobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "candidate_id", insertable = false, updatable = false)
    private CandidateEntity candidate;

    @ManyToOne
    @JoinColumn(name = "job_id", insertable = false, updatable = false)
    private Job job;

    @Column(name = "candidate_id")
    private UUID jobId;

    @Column(name = "job_id")
    private UUID candidateId;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
