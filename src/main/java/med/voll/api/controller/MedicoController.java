package med.voll.api.controller;


import jakarta.validation.Valid;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController // Anotação que marca a classe MedicoController como um controlador de requisições HTTP.
@RequestMapping("medicos") // Define o mapeamento base para as rotas relacionadas a este controlador ("/medicos").

public class MedicoController {

    @Autowired // Injeção de dependência do MedicoRepository, que permite acessar os dados no banco.
    private MedicoRepository repository;

    @PostMapping // Define que este método aceita requisições HTTP do tipo POST.
    @Transactional // Indica que este método é uma transação, ou seja, as operações realizadas aqui serão tratadas como uma única unidade de trabalho.
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriComponentsBuilder) {
        // Método responsável por cadastrar um novo médico no banco de dados.
        var medico = new Medico(dados); // Cria um novo objeto do tipo Medico com base nos dados recebidos na requisição.
        repository.save(medico); // Salva o objeto medico no banco de dados.

        // Cria a URI para a resposta de sucesso, indicando o caminho para acessar os detalhes do médico cadastrado.
        var uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        // Retorna uma resposta com status 201 Created, contendo a URI criada e os detalhes do médico cadastrado.
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping // Define que este método aceita requisições HTTP do tipo GET.
    public ResponseEntity<Page<DadosListagemMedico>> list(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        // Método responsável por listar os médicos cadastrados no banco de dados.

        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        // Obtém uma página de médicos ativos do banco de dados e mapeia cada um deles para um objeto do tipo DadosListagemMedico.

        // Retorna uma resposta com status 200 OK, contendo a lista paginada de médicos cadastrados.
        return ResponseEntity.ok(page);
    }

    @PutMapping // Define que este método aceita requisições HTTP do tipo PUT.
    @Transactional // Indica que este método é uma transação, ou seja, as operações realizadas aqui serão tratadas como uma única unidade de trabalho.
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        // Método responsável por atualizar as informações de um médico no banco de dados.

        var medico = repository.getReferenceById(dados.id());
        // Obtém uma referência ao médico com base no ID recebido na requisição, sem carregar todas as informações do médico na memória.

        medico.atualizarInformacoes(dados);
        // Atualiza as informações do médico com base nos dados recebidos na requisição.

        // Retorna uma resposta com status 200 OK, contendo os detalhes do médico atualizado.
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @DeleteMapping("/{id}") // Define que este método aceita requisições HTTP do tipo DELETE, com um parâmetro "id" na URL.
    @Transactional // Indica que este método é uma transação, ou seja, as operações realizadas aqui serão tratadas como uma única unidade de trabalho.
    public ResponseEntity excluir(@PathVariable Long id) {
        // Método responsável por excluir um médico do banco de dados com base no ID recebido na requisição.

        var medico = repository.getReferenceById(id);
        // Obtém uma referência ao médico com base no ID recebido na requisição, sem carregar todas as informações do médico na memória.

        medico.excluir();
        // Marca o médico como inativo no banco de dados, em vez de excluir permanentemente.

        // Retorna uma resposta com status 204 No Content, indicando que a exclusão foi realizada com sucesso, mas não há conteúdo para retornar.
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}") // Define que este método aceita requisições HTTP do tipo GET, com um parâmetro "id" na URL.
    public ResponseEntity detalhar(@PathVariable Long id) {
        // Método responsável por buscar e retornar os detalhes de um médico com base no ID recebido na requisição.

        var medico = repository.getReferenceById(id);
        // Obtém uma referência ao médico com base no ID recebido na requisição, sem carregar todas as informações do médico na memória.

        // Retorna uma resposta com status 200 OK, contendo os detalhes do médico encontrado.
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }
}

