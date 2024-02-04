package library_managment_system.app.exceptions;


public class NoSuchPatronRecordsExistsException
    extends RuntimeException {
 
    private String message;
 
    public NoSuchPatronRecordsExistsException() {}
 
    public NoSuchPatronRecordsExistsException(Long id)
    {
        super("User with id " + id + " has no borrowing records");
        this.message = "User with id " + id + " has no borrowing records";
    }
}