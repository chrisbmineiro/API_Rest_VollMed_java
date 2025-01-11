package med.voll.api.validations.cancelamento;

import med.voll.api.dto.consultas.CancelamentoConsultaDTO;

public interface ValidadorCancelamentoDeConsulta {
    void validarCancelamentoDeConsulta(CancelamentoConsultaDTO dados);
}
