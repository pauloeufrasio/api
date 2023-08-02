package med.voll.api.domain.medico;

import med.voll.api.domain.endereco.Endereco;

public record DadosDetalhamentoMedico(Long id, String nome, String email, String crm, String telefone, Especialidade especialidade, Endereco endereco) {

    // Construtor da classe que recebe um objeto Medico e cria um novo objeto DadosDetalhamentoMedico com base nos dados do Medico
    public DadosDetalhamentoMedico (Medico medico) {
        // Chamada ao construtor do registro DadosDetalhamentoMedico, passando os campos do objeto Medico
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(),medico.getEndereco());
    }

}
