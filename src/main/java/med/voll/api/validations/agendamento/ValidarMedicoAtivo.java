package med.voll.api.validations.agendamento;

import med.voll.api.dto.AgendamentoConsultaDTO;
import med.voll.api.exception.ValidacaoException;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarMedicoAtivo implements ValidadorAgendamentoDeConsulta{

    @Autowired
    private MedicoRepository medicoRepository;

    public void validarAgendamentoDeConsulta(AgendamentoConsultaDTO dados){
        if (dados.idMedico() == null){
            return;
        }

        var medicoEstaAtivo = medicoRepository.findAtivoById(dados.idMedico());
        if (!medicoEstaAtivo){
            throw new ValidacaoException("Médico não está ativo");
        }

    }
}
