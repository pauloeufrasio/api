package med.voll.api.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.endereco.DadosEndereco;

public record DadosCadastroMedico(
        @NotBlank // A anotação @NotBlank é semelhante à anotação @NotNull, mas é geralmente usada em campos de texto para indicar que o valor não pode ser nulo nem vazio (espaços em branco).
        String nome,
        @NotBlank
        @Email // Quando você aplica a anotação @Email a um campo de texto, você está indicando que o valor desse campo deve ser um endereço de e-mail válido de acordo com as regras de formatação.
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{11}", message = "O número de telefone deve ter 11 dígitos")
        String telefone,
        @NotNull
        @Pattern(regexp ="\\d{4,6}" ) // A anotação @Pattern é usada para validar se uma string corresponde a um padrão específico definido por uma expressão regular (regex). A expressão regular é especificada como um parâmetro dentro da anotação.
        String crm,
        @NotNull
        Especialidade especialidade,
        @NotNull @Valid DadosEndereco endereco) {

}
