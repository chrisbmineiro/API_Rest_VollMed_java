package med.voll.api.validations;

import med.voll.api.dto.AgendamentoConsultaDTO;
import med.voll.api.exception.ValidacaoException;

import java.time.Duration;
import java.time.LocalDateTime;

public class ValidarHorarioAntecedencia {
    public void validarAntecedencia(AgendamentoConsultaDTO dados) {
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferenca = Duration.between(agora, dataConsulta).toMinutes();
        if (diferenca < 30) {
            throw new ValidacaoException("Consulta deve ser agendada com no mínimo 30 minutos de antecedência");
        }
    }
}
