package med.voll.api.dto.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.dto.EnderecoDTO;

public record CadastroPacienteDTO(
        @NotBlank(message = "{nome.obrigatorio}")
        String nome,
        @NotBlank(message = "{email.obrigatorio}")
        @Email(message = "{email.invalido}")
        String email,
        @NotBlank(message = "{telefone.obrigatorio")
        @Pattern(regexp = "^(\\(\\d{2}\\)|\\d{2})\\s?\\d{4,5}-?\\d{4}$")
        String telefone,
        @NotBlank(message = "{cpf.obrigatorio}")
        @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}", message = "{cpf.invalido}")
        String cpf,
        @NotNull(message = "{endereco.obrigatorio}")
        @Valid
        EnderecoDTO endereco
) {
}
