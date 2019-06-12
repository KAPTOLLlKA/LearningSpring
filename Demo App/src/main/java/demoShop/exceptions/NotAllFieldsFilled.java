package demoShop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT)
public class NotAllFieldsFilled extends RuntimeException {
    public NotAllFieldsFilled() {
    }

    public NotAllFieldsFilled(String message) {
        super(message);
    }
}
