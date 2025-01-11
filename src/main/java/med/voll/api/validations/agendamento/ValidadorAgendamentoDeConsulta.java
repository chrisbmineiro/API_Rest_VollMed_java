package med.voll.api.validations.agendamento;

import med.voll.api.dto.consultas.AgendamentoConsultaDTO;

public interface ValidadorAgendamentoDeConsulta {
    void validarAgendamentoDeConsulta(AgendamentoConsultaDTO dados);
}
