package med.voll.api.validations.agendamento;

import med.voll.api.dto.AgendamentoConsultaDTO;
import med.voll.api.exception.ValidacaoException;
import med.voll.api.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarMedicoConsultaMesmoHorario implements ValidadorAgendamentoDeConsulta{

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validarAgendamentoDeConsulta(AgendamentoConsultaDTO dados){
        if (dados.idMedico() == null){
            return;
        }

        var medicoJaTemConsulta = consultaRepository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(dados.idMedico(), dados.data());
        if (medicoJaTemConsulta){
            throw new ValidacaoException("Médico já tem consulta marcada para este dia");
        }

    }
}
