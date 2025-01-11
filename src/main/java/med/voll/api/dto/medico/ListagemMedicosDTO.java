package med.voll.api.dto.medico;

import med.voll.api.models.Especialidade;
import med.voll.api.models.Medico;

public record ListagemMedicosDTO (
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade
) {
    public ListagemMedicosDTO(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
