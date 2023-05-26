package jvdb18.treestagramapi.exception;

public class UserAlreadyExistException extends RuntimeException {
 
    private String message;
 
    public UserAlreadyExistException() {}
 
    public UserAlreadyExistException(String msg)
    {
        super(msg);
        this.message = msg;
    }

}
