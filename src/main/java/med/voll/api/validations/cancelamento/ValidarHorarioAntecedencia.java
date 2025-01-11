package med.voll.api.validations.cancelamento;

import med.voll.api.dto.AgendamentoConsultaDTO;
import med.voll.api.dto.CancelamentoConsultaDTO;
import med.voll.api.exception.ValidacaoException;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.validations.agendamento.ValidadorAgendamentoDeConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorHorarioAntecedenciaCancelamento")
public class ValidarHorarioAntecedencia implements ValidadorCancelamentoDeConsulta {

    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validarCancelamentoDeConsulta(CancelamentoConsultaDTO dados) {
        var consulta = repository.getReferenceById(dados.idConsulta());
        var agora = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(agora, consulta.getData()).toHours();

        if (diferencaEmHoras < 24) {
            throw new ValidacaoException("Consulta somente pode ser cancelada com antecedência mínima de 24h!");
        }
    }
}
