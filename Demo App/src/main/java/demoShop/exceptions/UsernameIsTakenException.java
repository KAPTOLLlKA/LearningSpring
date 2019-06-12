package demoShop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class UsernameIsTakenException extends RuntimeException {
    public UsernameIsTakenException() {
    }

    public UsernameIsTakenException(String message) {
        super(message);
    }
}
