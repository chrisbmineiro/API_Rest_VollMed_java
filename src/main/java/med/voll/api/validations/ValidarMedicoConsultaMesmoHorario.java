package med.voll.api.validations;

import med.voll.api.dto.AgendamentoConsultaDTO;
import med.voll.api.exception.ValidacaoException;
import med.voll.api.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidarMedicoConsultaMesmoHorario {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validarMedicoConsultaMesmoHorario(AgendamentoConsultaDTO dados){
        if (dados.idMedico() == null){
            return;
        }

        var medicoJaTemConsulta = consultaRepository.existsByMedicoIdAndData(dados.idMedico(), dados.data());
        if (medicoJaTemConsulta){
            throw new ValidacaoException("Médico já tem consulta marcada para este dia");
        }

    }
}
