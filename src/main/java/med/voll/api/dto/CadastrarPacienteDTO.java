package med.voll.api.dto;

public record CadastrarPacienteDTO(
        String nome,
        String email,
        String telefone,
        String cpf,
        EnderecoDTO endereco
) {
}
