package med.voll.api.models;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.dto.CadastroPacienteDTO;

@Entity(name = "Paciente")
@Table(name = "pacientes")
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String cpf;
    private String telefone;

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public Paciente(){
        // Construtor padrão
    }

    @Embedded
    private Endereco endereco;

    public Paciente(CadastroPacienteDTO dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.endereco = new Endereco(dados.endereco());
    }

}
