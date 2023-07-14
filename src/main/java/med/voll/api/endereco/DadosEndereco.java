package med.voll.api.endereco;
// Classe do tipo record responsavel pelo endereço
public record DadosEndereco(String logradouro, String bairro, String cep, String cidade, String uf, String complemento, String numero) {
}
