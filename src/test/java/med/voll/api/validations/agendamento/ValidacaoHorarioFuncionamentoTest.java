package med.voll.api.validations.agendamento;

import med.voll.api.dto.consultas.AgendamentoConsultaDTO;
import med.voll.api.exception.ValidacaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidacaoHorarioFuncionamentoTest {

    private final ValidacaoHorarioFuncionamento validador = new ValidacaoHorarioFuncionamento();

    private ValidacaoHorarioFuncionamento validacaoHorarioFuncionamento;

    @BeforeEach
    void setup() {
        validacaoHorarioFuncionamento = new ValidacaoHorarioFuncionamento();
    }

    @Test
    void deveLancarExcecaoParaConsultaEmDomingo() {
        var dados = new AgendamentoConsultaDTO(LocalDateTime.of(2025, 1, 12, 10, 0)); // Domingo
        assertThrows(ValidacaoException.class, () -> validacaoHorarioFuncionamento.validarAgendamentoDeConsulta(dados));
    }

    @Test
    void deveLancarExcecaoParaConsultaAntesDasSete() {
        var dados = new AgendamentoConsultaDTO(LocalDateTime.of(2025, 1, 11, 6, 59)); // Sábado antes das 7h
        assertThrows(ValidacaoException.class, () -> validacaoHorarioFuncionamento.validarAgendamentoDeConsulta(dados));
    }

    @Test
    void deveLancarExcecaoParaConsultaAposAsDezoito() {
        var dados = new AgendamentoConsultaDTO(LocalDateTime.of(2025, 1, 11, 19, 1)); // Sábado após 18h
        assertThrows(ValidacaoException.class, () -> validacaoHorarioFuncionamento.validarAgendamentoDeConsulta(dados));
    }

    @Test
    void naoDeveLancarExcecaoParaConsultaDentroDoHorarioPermitido() {
        var dados = new AgendamentoConsultaDTO(LocalDateTime.of(2025, 1, 11, 10, 0)); // Sábado às 10h
        assertDoesNotThrow(() -> validacaoHorarioFuncionamento.validarAgendamentoDeConsulta(dados));
    }

    @Test
    void deveLancarExcecaoParaDataNula() {
        var dados = new AgendamentoConsultaDTO(null);
        assertThrows(NullPointerException.class, () -> validacaoHorarioFuncionamento.validarAgendamentoDeConsulta(dados));
    }

    @Test
    void naoDeveLancarExcecaoParaConsultaExatamenteAsSete() {
        var dados = new AgendamentoConsultaDTO(LocalDateTime.of(2025, 1, 11, 7, 0)); // Sábado às 7h
        assertDoesNotThrow(() -> validacaoHorarioFuncionamento.validarAgendamentoDeConsulta(dados));
    }

    @Test
    void naoDeveLancarExcecaoParaConsultaExatamenteAsDezoito() {
        var dados = new AgendamentoConsultaDTO(LocalDateTime.of(2025, 1, 11, 18, 0)); // Sábado às 18h
        assertDoesNotThrow(() -> validacaoHorarioFuncionamento.validarAgendamentoDeConsulta(dados));
    }

    @Test
    void devePermitirAgendamentoEmHorarioValido() {
        // Arrange
        var dados = Mockito.mock(AgendamentoConsultaDTO.class);
        Mockito.when(dados.data()).thenReturn(LocalDateTime.of(2025, 1, 15, 10, 0)); // Quarta-feira, 10:00

        // Act & Assert
        assertDoesNotThrow(() -> validador.validarAgendamentoDeConsulta(dados));
    }

    @Test
    void deveLancarExcecaoQuandoHorarioForAntesDasSete() {
        // Arrange
        var dados = Mockito.mock(AgendamentoConsultaDTO.class);
        Mockito.when(dados.data()).thenReturn(LocalDateTime.of(2025, 1, 15, 6, 0)); // Quarta-feira, 06:00

        // Act
        Executable action = () -> validador.validarAgendamentoDeConsulta(dados);

        // Assert
        assertThrows(ValidacaoException.class, action, "Horário de funcionamento da clínica: 07:00 às 19:00");
    }

    @Test
    void deveLancarExcecaoQuandoHorarioForAposAsDezoito() {
        // Arrange
        var dados = Mockito.mock(AgendamentoConsultaDTO.class);
        Mockito.when(dados.data()).thenReturn(LocalDateTime.of(2025, 1, 15, 19, 30)); // Quarta-feira, 19:30

        // Act
        Executable action = () -> validador.validarAgendamentoDeConsulta(dados);

        // Assert
        assertThrows(ValidacaoException.class, action, "Horário de funcionamento da clínica: 07:00 às 19:00");
    }

    @Test
    void deveLancarExcecaoQuandoAgendamentoForNoDomingo() {
        // Arrange
        var dados = Mockito.mock(AgendamentoConsultaDTO.class);
        Mockito.when(dados.data()).thenReturn(LocalDateTime.of(2025, 1, 12, 10, 0)); // Domingo, 10:00

        // Act
        Executable action = () -> validador.validarAgendamentoDeConsulta(dados);

        // Assert
        assertThrows(ValidacaoException.class, action, "Horário de funcionamento da clínica: 07:00 às 19:00");
    }
}
