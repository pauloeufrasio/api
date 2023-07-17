package med.voll.api.controller;


import jakarta.validation.Valid;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.DadosListagemMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping //@PostMapping: Determina que o método aceitará requisições HTTP do tipo POST.
    // @RequestBody indica que um parâmetro de método deve ser associado ao valor do corpo da solicitação HTTP
    @Transactional // anotação
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){ // JSON (JavaScript Object Notation) é um formato utilizado para representação de informações, assim como XML e CSV.
        repository.save(new Medico(dados));
    }
    @GetMapping // @GetMapping é uma anotação do Spring Framework usada para mapear uma requisição HTTP GET para um método específico em um controlador (controller) de uma aplicação web.
    public List<DadosListagemMedico> list() { // retorno do método list() é uma lista de objetos do tipo DadosListagemMedico. Esse método utiliza o método findAll() do objeto repository para obter todos os registros do banco de dados relacionados à entidade Medico. Esses registros são então convertidos em uma stream (fluxo) e mapeados para objetos DadosListagemMedico por meio do método de referência DadosListagemMedico::new. Finalmente, a stream é coletada em uma lista usando o método toList().
        return repository.findAll().stream().map(DadosListagemMedico::new).toList();

    }

}
