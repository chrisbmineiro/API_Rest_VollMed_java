package med.voll.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime data,

        Especialidade especialidade
) {
}
