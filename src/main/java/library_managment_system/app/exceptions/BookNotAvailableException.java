package library_managment_system.app.exceptions;


public class BookNotAvailableException
    extends RuntimeException {
 
    private String message;
 
    public BookNotAvailableException() {}
 
    public BookNotAvailableException(Long id)
    {
        super("Book with id :"+ id +" not available right now");
        this.message = "Book with id :"+ id +" not available right now";
    }
}