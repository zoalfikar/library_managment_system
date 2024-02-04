package library_managment_system.app.exceptions;


public class NoSuchPatronExistsException
    extends RuntimeException {
 
    private String message;
 
    public NoSuchPatronExistsException() {}
 
    public NoSuchPatronExistsException(Long id)
    {
        super("User not found : invalid id " + id);
        this.message = "User not found : invalid id " + id;
    }
}