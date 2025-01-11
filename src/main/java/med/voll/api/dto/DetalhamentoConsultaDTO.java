package med.voll.api.dto;

import java.time.LocalDateTime;

public record DetalhamentoConsultaDTO(
        Long id,
        String idMedico,
        String idPaciente,
        LocalDateTime data
) {
}
