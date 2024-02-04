package library_managment_system.app.exceptions;


public class NoSuchRecordExistsException
    extends RuntimeException {
 
    private String message;
 
    public NoSuchRecordExistsException() {}
 
    public NoSuchRecordExistsException(Long bookId, Long patronId)
    {
        super("user with id : "+patronId+"  did not borrow the book with id " + bookId);
        this.message = "Can't excute ( return book ) : user with id : "+patronId+"  has not borrowed the book with id " + bookId;
    }
}