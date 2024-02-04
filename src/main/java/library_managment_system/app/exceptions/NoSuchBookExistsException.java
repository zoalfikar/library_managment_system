package library_managment_system.app.exceptions;


public class NoSuchBookExistsException
    extends RuntimeException {
 
    private String message;
 
    public NoSuchBookExistsException() {}
 
    public NoSuchBookExistsException(Long id)
    {
        super("Book not found : invalid id " + id);
        this.message = "Book not found : invalid id " + id;
    }
}