package med.voll.api.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("medicos")
public class MedicoController {

    @PostMapping
    public void cadastrar(@RequestBody String json){ // JSON (JavaScript Object Notation) é um formato utilizado para representação de informações, assim como XML e CSV.
        System.out.println(json);

    }

}
