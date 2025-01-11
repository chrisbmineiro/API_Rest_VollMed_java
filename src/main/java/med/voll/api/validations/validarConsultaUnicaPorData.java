package med.voll.api.validations;

import med.voll.api.dto.AgendamentoConsultaDTO;
import med.voll.api.exception.ValidacaoException;
import med.voll.api.repository.ConsultaRepository;

public class validarConsultaUnicaPorData {

    private ConsultaRepository consultaRepository;

    public void validarConsultaUnicaPorData(AgendamentoConsultaDTO dados){
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var pacientePossuiConsulta = consultaRepository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario, ultimoHorario);
        if (pacientePossuiConsulta){
            throw new ValidacaoException("Paciente já tem consulta marcada para este dia");
        }
    }
}
