package med.voll.api.dto.consultas;

import med.voll.api.models.Consulta;

import java.time.LocalDateTime;

public class ListagemConsultasDTO {

    private Long id;
    private String nomeMedico;
    private String nomePaciente;
    private LocalDateTime data;

    public ListagemConsultasDTO(Consulta consulta) {
        this.id = consulta.getId();
        this.nomeMedico = consulta.getMedico().getNome();
        this.nomePaciente = consulta.getPaciente().getNome();
        this.data = consulta.getData();
    }

    public Long getId() {
        return id;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public LocalDateTime getData() {
        return data;
    }
}

