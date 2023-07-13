package med.voll.api.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("medicos")
public class MedicoController {

    @PostMapping //@PostMapping: Determina que o método aceitará requisições HTTP do tipo POST.
    // @RequestBody: Esta anotação é usada para anotar os argumentos do método do manipulador de solicitações.
    public void cadastrar(@RequestBody String json){ // JSON (JavaScript Object Notation) é um formato utilizado para representação de informações, assim como XML e CSV.
        System.out.println(json);

    }

}
