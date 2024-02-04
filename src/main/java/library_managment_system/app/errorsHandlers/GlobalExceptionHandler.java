package library_managment_system.app.errorsHandlers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.dao.DataIntegrityViolationException ;
import org.springframework.dao.DataAccessException;

import library_managment_system.app.exceptions.NoSuchBookExistsException;
import library_managment_system.app.exceptions.NoSuchPatronExistsException;
import library_managment_system.app.exceptions.NoSuchRecordExistsException;
import library_managment_system.app.exceptions.NoSuchBookRecordsExistsException;
import library_managment_system.app.exceptions.NoSuchPatronRecordsExistsException;
import library_managment_system.app.exceptions.BookNotAvailableException;



@RestControllerAdvice
public class GlobalExceptionHandler {

    private Object exceptionErorr(Exception ex){
        Map<String, String> errorBody = new HashMap<>();
        errorBody.put("error", ex.getMessage());
        return errorBody;
    }

    private Object getValidationErrorsMap(Map<String, Object> errors) {
        Map<String, Object> errorsResponseBody = new HashMap<>();
        errorsResponseBody.put("errors", errors);
        return errorsResponseBody;
    }


	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        Map<String, Object> errorsBody = new HashMap<>();
        for (FieldError e : errors){
            errorsBody.put(e.getField() , e.getDefaultMessage());
        }
        return new ResponseEntity<>(this.getValidationErrorsMap(errorsBody),new HttpHeaders() , HttpStatus.UNPROCESSABLE_ENTITY);
    }



    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException .class)
    public ResponseEntity<Object>  handleDataIntegrityViolationException (DataIntegrityViolationException  ex) {
        Map<String, Object> body = new HashMap<>();
        String msg = ex.getMessage().toLowerCase();
        if(msg.contains("email") && msg.contains("duplicate") && msg.contains("entry")  ){  
            body.put("error", "email is already has been registered!");
                return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }
        if(msg.contains("isbn") && msg.contains("duplicate") && msg.contains("entry")  ){  
            body.put("error", "book is already has been registered!");
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }
        else{
            throw ex ;
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataAccessException .class)
    public ResponseEntity<Object>  handleDataAccessException (DataAccessException  ex) {
        Map<String, Object> body = new HashMap<>();
        String msg = ex.getMessage().toLowerCase();
        if(msg.contains("book") && msg.contains("not") && msg.contains("available")  ){  
            body.put("message", "BOOK NOT AVAILABLE RIGHT NOW");
                return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
        }
        else{
            throw ex ;
        }
    }

    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoSuchBookExistsException .class)
    public ResponseEntity<Object>  handleNoSuchBookExistsException (NoSuchBookExistsException  ex) {
        return new ResponseEntity<>(this.exceptionErorr(ex),new HttpHeaders() , HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoSuchPatronExistsException .class)
    public ResponseEntity<Object>  handleNoSuchPatronExistsException (NoSuchPatronExistsException  ex) {
        return new ResponseEntity<>(this.exceptionErorr(ex),new HttpHeaders() , HttpStatus.BAD_REQUEST);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoSuchRecordExistsException .class)
    public ResponseEntity<Object>  handleNoSuchRecordExistsException (NoSuchRecordExistsException  ex) {
        return new ResponseEntity<>(this.exceptionErorr(ex),new HttpHeaders() , HttpStatus.BAD_REQUEST);
    }

 
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoSuchBookRecordsExistsException .class)
    public ResponseEntity<Object>  handleNoSuchBookRecordsExistsException (NoSuchBookRecordsExistsException  ex) {
        return new ResponseEntity<>(this.exceptionErorr(ex),new HttpHeaders() , HttpStatus.BAD_REQUEST);
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoSuchPatronRecordsExistsException .class)
    public ResponseEntity<Object>  handleNoSuchPatronRecordsExistsException (NoSuchPatronRecordsExistsException  ex) {
        return new ResponseEntity<>(this.exceptionErorr(ex),new HttpHeaders() , HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BookNotAvailableException .class)
    public ResponseEntity<Object>  handleBookNotAvailableException (BookNotAvailableException  ex) {
        return new ResponseEntity<>(this.exceptionErorr(ex),new HttpHeaders() , HttpStatus.BAD_REQUEST);
    }

}