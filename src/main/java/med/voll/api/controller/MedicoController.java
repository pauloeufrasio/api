package med.voll.api.controller;


import jakarta.validation.Valid;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
