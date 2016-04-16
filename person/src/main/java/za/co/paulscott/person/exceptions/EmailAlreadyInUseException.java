package za.co.paulscott.person.exceptions;

/**
 * Created by pscot on 3/7/2016.
 */
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class EmailAlreadyInUseException extends RuntimeException {

}