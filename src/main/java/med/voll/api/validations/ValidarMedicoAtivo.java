package med.voll.api.validations;

import med.voll.api.dto.AgendamentoConsultaDTO;
import med.voll.api.exception.ValidacaoException;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidarMedicoAtivo {

    @Autowired
    private MedicoRepository medicoRepository;

    public void validarMedicoAtivo(AgendamentoConsultaDTO dados){
        if (dados.idMedico() == null){
            return;
        }

        var medicoEstaAtivo = medicoRepository.findAtivoById(dados.idMedico());
        if (!medicoEstaAtivo){
            throw new ValidacaoException("Médico não está ativo");
        }

    }
}
