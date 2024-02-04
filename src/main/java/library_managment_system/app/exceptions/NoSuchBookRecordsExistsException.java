package library_managment_system.app.exceptions;


public class NoSuchBookRecordsExistsException
    extends RuntimeException {
 
    private String message;
 
    public NoSuchBookRecordsExistsException() {}
 
    public NoSuchBookRecordsExistsException(Long id)
    {
        super("Book with id " + id + " has no borrowing records");
        this.message = "Book with id " + id + " has no borrowing records";
    }
}