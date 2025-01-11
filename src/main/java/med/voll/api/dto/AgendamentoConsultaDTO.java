package med.voll.api.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AgendamentoConsultaDTO(
        Long idMedico,
        @NotNull
        Long idPaciente,
        @Future @NotNull
        LocalDateTime data
) {
}
