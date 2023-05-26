package jvdb18.treestagramapi.exception;

public class NoSuchUserException  extends RuntimeException {
 
 
    public NoSuchUserException() {}
 
    public NoSuchUserException(String msg)
    {
        super(msg);
    }
    
}
