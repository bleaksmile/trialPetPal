package trialpetpal.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ErrorHandlingAdvice {

  @ResponseBody
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  ErrorMsg missingParams(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
      errors.put(fieldError.getField(), fieldError.getDefaultMessage());
    }
    return new ErrorMsg("error", "Missing parameter(s): " + errors);
  }

  @ResponseBody
  @ExceptionHandler(EmailTakenException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  ErrorMsg usernameTaken(EmailTakenException ex) {
    return new ErrorMsg("error", ex.getMessage());
  }

  @ResponseBody
  @ExceptionHandler(AnimalIdNotFoundException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  ErrorMsg animalNotFound(EmailTakenException ex) {
    return new ErrorMsg("error", ex.getMessage());
  }

  @ResponseBody
  @ExceptionHandler(UserNotFoundException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  ErrorMsg userIdNotFound(EmailTakenException ex) {
    return new ErrorMsg("error", ex.getMessage());
  }

  @ResponseBody
  @ExceptionHandler(UserIsNullException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  ErrorMsg userIsNull(EmailTakenException ex) {
    return new ErrorMsg("error", ex.getMessage());
  }
  @ResponseBody
  @ExceptionHandler(AnimalAlreadyAdoptedException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  ErrorMsg animalAlreadyAdopted(AnimalAlreadyAdoptedException ex) {
    return new ErrorMsg("error", ex.getMessage());
  }

}
