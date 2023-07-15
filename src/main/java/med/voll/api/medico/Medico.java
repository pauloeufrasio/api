package med.voll.api.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.Endereco;

@Table (name = "medicos") // Essa anotação é usada para fornecer detalhes adicionais sobre a tabela associada a uma entidade. Ela pode ser usada para especificar o nome da tabela.JPA
@Entity(name = "Medico") // Essa anotação é usada para marcar uma classe como uma entidade. Uma entidade é uma classe que representa uma tabela no banco de dados.JPA
@Getter // Essa anotação é usada para gerar automaticamente métodos getters para os campos da classe.Lombok
@NoArgsConstructor // Essa anotação gera automaticamente um construtor sem argumentos para a classe.Lombok
@AllArgsConstructor // Essa anotação gera um construtor que aceita todos os campos da classe como argumentos.Lombok
@EqualsAndHashCode(of = "id")
public class Medico { // JPA para representar um tabela no banco de dados.

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String email;
    private String crm;

    @Enumerated(EnumType.STRING)
    Especialidade especialidade;

    @Embedded
    private Endereco endereco;

}

