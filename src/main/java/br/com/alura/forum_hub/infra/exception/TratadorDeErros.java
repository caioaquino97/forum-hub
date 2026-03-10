package br.com.alura.forum_hub.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice // Diz ao Spring que esta classe "vigia" os erros dos Controllers
public class TratadorDeErros {

    // Erro 404: Quando o ID não existe no banco de dados
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    // Erro 400: Quando o usuário preenche o formulário errado (validação)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
        // Pega a lista de erros que o Spring encontrou
        var erros = ex.getFieldErrors();

        // Transforma a lista técnica do Spring numa lista simplificada para o usuário
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }

    // Record interno para organizar como o erro de validação aparece (ex: "campo: mensagem")
    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}