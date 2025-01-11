package med.voll.api.validations.agendamento;

import med.voll.api.dto.AgendamentoConsultaDTO;
import med.voll.api.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorHorarioAntecedenciaAgendamento")
public class ValidarHorarioAntecedencia implements ValidadorAgendamentoDeConsulta {
    public void validarAgendamentoDeConsulta(AgendamentoConsultaDTO dados) {
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferenca = Duration.between(agora, dataConsulta).toMinutes();
        if (diferenca < 30) {
            throw new ValidacaoException("Consulta deve ser agendada com no mínimo 30 minutos de antecedência");
        }
    }
}
