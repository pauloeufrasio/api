package med.voll.api.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // @RestControllerAdvice, ela atua como um interceptador para exceções lançadas pelos controladores durante o processamento das requisições HTTP,Isso significa que você pode centralizar o tratamento de exceções em um único lugar em vez de ter que lidar com elas em cada método de cada controlador individualmente.
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class) // @ExceptionHandler é útil para manter o tratamento de exceções relacionado a um determinado controlador ou para casos específicos em um único local, em vez de espalhar o tratamento de exceções em diversos lugares do código
    public ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErrosValidacoes::new).toList());
    }
    private record DadosErrosValidacoes(String campo,String mensagem) { // DTO implementada dentro da propria classe TratadorDeErros
        public DadosErrosValidacoes(FieldError error) { // FieldError encapsula informações sobre o erro de validação, como o nome do campo que falhou na validação, a mensagem de erro associada a esse campo e o valor rejeitado.
            this(error.getField(), error.getDefaultMessage());


        }

    }

}
