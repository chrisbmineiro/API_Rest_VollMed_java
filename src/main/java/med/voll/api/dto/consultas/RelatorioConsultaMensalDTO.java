package med.voll.api.dto.consultas;

import med.voll.api.models.Medico;

public record RelatorioConsultaMensalDTO(
        String nome,
        String crm,
        Long quantidadeConsultasNoMes
){
    public RelatorioConsultaMensalDTO(Medico medico, Long quantidadeConsultasNoMes) {
        this(medico.getNome(), medico.getCrm(), quantidadeConsultasNoMes);
    }
}
