package med.voll.api.dto;

import med.voll.api.models.Especialidade;
import med.voll.api.models.Medico;

public record ListagemMedicosDTO (
        String nome,
        String email,
        String crm,
        Especialidade especialidade
) {
    public ListagemMedicosDTO(Medico medico){
        this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
