package pokemonapi.resttemplate.service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Ocorreu um erro. Por favor, entrar em contato com o suporte técnico.";
    private HttpStatus status;

    public BusinessException(HttpStatus status) {
        super(DEFAULT_MESSAGE);
        this.status = status;
    }

    public BusinessException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

}
