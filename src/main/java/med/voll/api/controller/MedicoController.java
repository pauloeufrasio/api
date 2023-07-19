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

@RestController
@RequestMapping ("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository; // Injeção de dependencia

    @PostMapping //@PostMapping: Determina que o método aceitará requisições HTTP do tipo POST.
    // @RequestBody indica que um parâmetro de método deve ser associado ao valor do corpo da solicitação HTTP
    @Transactional // anotação de transação
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriComponentsBuilder){ // Classe DTO DadosCadastroMeddico
        var medico =new Medico(dados);
        repository.save(medico);

        var uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));

    }
    @GetMapping // @GetMapping é uma anotação do Spring Framework usada para mapear uma requisição HTTP GET para um método específico em um controlador (controller) de uma aplicação web.
    public ResponseEntity <Page<DadosListagemMedico>> list(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) { // retorno do método list() é uma lista de objetos do tipo DadosListagemMedico. Esse método utiliza o método findAll() do objeto repository para obter todos os registros do banco de dados relacionados à entidade Medico. Esses registros são então convertidos em uma stream (fluxo) e mapeados para objetos DadosListagemMedico por meio do método de referência DadosListagemMedico::new. Finalmente, a stream é coletada em uma lista usando o método toList().
        var page =repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        return ResponseEntity.ok(page);

    }
    @PutMapping
    @Transactional //@Transactional, você está indicando ao framework que o método deve ser executado dentro de uma transação. Se a execução do método for bem-sucedida, a transação será confirmada
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
       var medico = repository.getReferenceById(dados.id());
       medico.atualizarInformacoes(dados);
       return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));

    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity exluir(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.exluir();
        return ResponseEntity.noContent().build();

    }
    @GetMapping("/{id}")
    public  ResponseEntity detalhar(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }


}
