package med.voll.api.dto;

public record EnderecoDTO (
        String logradouro,
        String bairro,
        String cep,
        String cidade,
        String uf,
        String complemento,
        String numero
){
}
