package med.voll.api.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.Endereco;

@Table(name = "medicos")
// Essa anotação é usada para fornecer detalhes adicionais sobre a tabela associada a uma entidade. Ela pode ser usada para especificar o nome da tabela.JPA
@Entity
// Essa anotação é usada para marcar uma classe como uma entidade. Uma entidade é uma classe que representa uma tabela no banco de dados.JPA
@Getter // Essa anotação é usada para gerar automaticamente métodos getters para os campos da classe.Lombok
@NoArgsConstructor // Essa anotação gera automaticamente um construtor sem argumentos para a classe.Lombok
@AllArgsConstructor // Essa anotação gera um construtor que aceita todos os campos da classe como argumentos.Lombok
@EqualsAndHashCode(of = "id")
public class Medico { // JPA para representar um tabela no banco de dados.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String email;
    private String crm;

    private  String telefone;

    @Enumerated(EnumType.STRING)
    Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    public Medico(DadosCadastroMedico dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes(DadosAtualizacaoMedico dados) {
        if (dados.nome() !=  null ) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null ){
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null){
            this.endereco.atualizarInformacoesEndereco(dados.endereco());
        }
    }
}

