package med.voll.api.repository;

import med.voll.api.dto.consultas.RelatorioConsultaMensalDTO;
import med.voll.api.models.Consulta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    boolean existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(Long idMedico, LocalDateTime data);

    boolean existsByPacienteIdAndDataBetween(Long idPaciente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

    @Query("SELECT c FROM Consulta c WHERE c.status = 'AGENDADA'")
    Page<Consulta> findAllByStatusAgendado(Pageable pageable);

    @Query("""
         SELECT new med.voll.api.dto.consultas.RelatorioConsultaMensalDTO(m.nome, m.crm, COUNT(c))
         FROM Consulta c JOIN c.medico m
         WHERE c.data >= :inicioMes AND c.data <= :fimMes
         GROUP BY m.nome, m.crm
         """)
    List<RelatorioConsultaMensalDTO> gerarRelatorioConsultaMensal(LocalDateTime inicioMes, LocalDateTime fimMes);

}
