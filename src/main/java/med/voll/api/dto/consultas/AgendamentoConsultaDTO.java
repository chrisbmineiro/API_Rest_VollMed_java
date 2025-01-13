package med.voll.api.dto.consultas;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.models.Especialidade;

import java.time.LocalDateTime;

public record AgendamentoConsultaDTO(

        Long idMedico,

        @NotNull
        Long idPaciente,

        @Future
        @NotNull
        LocalDateTime data,

        Especialidade especialidade
) {
        public AgendamentoConsultaDTO(LocalDateTime of) {
                this(null, null, of, null);
        }
}
