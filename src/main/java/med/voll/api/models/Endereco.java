package med.voll.api.models;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import med.voll.api.dto.EnderecoDTO;

@Embeddable
@Getter
@AllArgsConstructor
public class Endereco {
    @NotBlank(message = "Logradouro é obrigatório")
    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    // Construtor padrão necessário para o JPA
    public Endereco() {
        // Deixe vazio
    }

    public Endereco(EnderecoDTO endereco) {
        this.logradouro = endereco.logradouro();
        this.bairro = endereco.bairro();
        this.cep = endereco.cep();
        this.uf = endereco.uf();
        this.cidade = endereco.cidade();
        this.numero = endereco.numero();
        this.complemento = endereco.complemento();
    }
}