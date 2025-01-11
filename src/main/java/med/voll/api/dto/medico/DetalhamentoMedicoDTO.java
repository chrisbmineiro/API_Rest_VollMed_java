package med.voll.api.dto.medico;

import med.voll.api.models.Endereco;
import med.voll.api.models.Especialidade;
import med.voll.api.models.Medico;

public record DetalhamentoMedicoDTO(
        Long id,
        String nome,
        String email,
        String crm,
        String telefone,
        Especialidade especialidade,
        Endereco endereco
) {
    public DetalhamentoMedicoDTO(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
    }
}
