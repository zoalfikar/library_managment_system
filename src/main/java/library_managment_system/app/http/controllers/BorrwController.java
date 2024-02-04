package library_managment_system.controllers;

import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import java.sql.Timestamp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import library_managment_system.repositories.*;
import library_managment_system.models.*;
import library_managment_system.app.exceptions.NoSuchPatronExistsException;
import library_managment_system.app.exceptions.NoSuchBookExistsException;
import library_managment_system.app.exceptions.NoSuchRecordExistsException;
import library_managment_system.app.exceptions.NoSuchPatronRecordsExistsException;
import library_managment_system.app.exceptions.NoSuchBookRecordsExistsException; 
import library_managment_system.app.exceptions.BookNotAvailableException; 
import org.springframework.transaction.annotation.Transactional;

import static library_managment_system.controllers.helper.IndexController.sendSuccessMessage;

@RestController	
@RequestMapping(path="api/")
public class BorrwController {
	@Autowired 
	private BorrowRepository borrowRepository;

	@Autowired 
	private PatronRepository patronRepository;

	@Autowired 
	private BookRepository bookRepository;

	@Transactional
	@PostMapping(path="borrow/{bookId}/patron/{patronId}") 
	public @ResponseBody Object borrw (@PathVariable Long patronId  ,@PathVariable Long bookId) {
		Patron patron =  patronRepository.findById(patronId).orElseThrow(() -> new NoSuchPatronExistsException(patronId));
		Book book =  bookRepository.findById(bookId).orElseThrow(() -> new NoSuchBookExistsException(bookId));
		this.bookAvailable(bookId);
		BorrowingRecord record = new BorrowingRecord();
		record.setPatronId(patron.getId());
        record.setBookId(book.getId());
		borrowRepository.save(record);
		return sendSuccessMessage("borrwing details have been saved successfully!");
	}

	@Transactional
	@PutMapping(path="return/{bookId}/patron/{patronId}") 
	public @ResponseBody Object returnBook (@PathVariable Long patronId  ,@PathVariable Long bookId) {
        BorrowingRecord record ;
        record =  borrowRepository.findByBookIdAndPatronIdAndReturnedDateIsNull(bookId ,patronId);

        if (record == null) {
            throw new NoSuchRecordExistsException(bookId , patronId);
        }
        Date d = new Date();
        record.setReturnedDate(new Timestamp(d.getTime()));
		borrowRepository.save(record);
		return sendSuccessMessage("book has been returned successfully!");
	}

	@GetMapping(path="/borrowing/records/patron/{patronId}")
	public @ResponseBody Iterable<BorrowingRecord>  getAllPatronsRecords(@PathVariable Long patronId) {
		Patron p = patronRepository.findById(patronId).orElseThrow(() -> new NoSuchPatronExistsException(patronId));
		if (p.getBorrowingRecords() == null) {
			throw new NoSuchPatronRecordsExistsException(patronId);
		}
		 return p.getBorrowingRecords();
	}


	@GetMapping(path="/borrowing/records/book/{bookId}")
	public @ResponseBody Iterable<BorrowingRecord>  getAllBookRecords(@PathVariable Long bookId) {
		Book b = bookRepository.findById(bookId).orElseThrow(() -> new NoSuchBookExistsException(bookId));
		if (b.getBorrowingRecords() == null) {
			throw new NoSuchBookRecordsExistsException(bookId);
		}
		 return b.getBorrowingRecords();
	}

	private boolean  bookAvailable(Long id){
		BorrowingRecord record = borrowRepository.findByBookIdAndReturnedDateIsNull(id);
		if (record == null) {
			return true;
		} else {
			throw new BookNotAvailableException(id);
		}
	}

}