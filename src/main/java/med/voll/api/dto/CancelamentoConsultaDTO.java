package med.voll.api.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.models.MotivoCancelamento;

public record CancelamentoConsultaDTO (
        @NotNull
        Long idConsulta,

        @NotNull
        MotivoCancelamento motivo
) {
}
