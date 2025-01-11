package med.voll.api.validations;

import med.voll.api.dto.AgendamentoConsultaDTO;
import med.voll.api.exception.ValidacaoException;

import java.time.DayOfWeek;

public class ValidacaoHorarioFuncionamento {

    public void validarHorario(AgendamentoConsultaDTO dados) {
        var dataConsulta = dados.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDasOito = dataConsulta.getHour() < 7;
        var depoisDasDezoito = dataConsulta.getHour() > 18;
        if (domingo || antesDasOito || depoisDasDezoito) {
            throw new ValidacaoException("Horário de funcionamento da clínica: 07:00 às 19:00");
        }
    }
}
