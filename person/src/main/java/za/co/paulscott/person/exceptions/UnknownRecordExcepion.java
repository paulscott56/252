package za.co.paulscott.person.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by paul on 2016/03/09.
 */
@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class UnknownRecordExcepion extends RuntimeException {

}
