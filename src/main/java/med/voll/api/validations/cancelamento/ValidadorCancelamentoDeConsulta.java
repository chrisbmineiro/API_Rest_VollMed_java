package med.voll.api.validations.cancelamento;

import med.voll.api.dto.CancelamentoConsultaDTO;

public interface ValidadorCancelamentoDeConsulta {
    void validarCancelamentoDeConsulta(CancelamentoConsultaDTO dados);
}
