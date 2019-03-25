package trialpetpal.demo.exception;

public class EmailTakenException extends Exception {
  
  public EmailTakenException(String message) {
    super(message);
  }
}
