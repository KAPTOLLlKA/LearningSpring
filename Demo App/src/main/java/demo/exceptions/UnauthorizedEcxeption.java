package demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorizedEcxeption extends RuntimeException {
    public UnauthorizedEcxeption() {
    }

    public UnauthorizedEcxeption(String message) {
        super(message);
    }
}
