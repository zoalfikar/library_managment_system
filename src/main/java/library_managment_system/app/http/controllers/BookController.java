package library_managment_system.controllers;

import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import library_managment_system.repositories.BookRepository;
import library_managment_system.models.Book;
import library_managment_system.app.http.requests.BookValidator;
import library_managment_system.app.exceptions.NoSuchBookExistsException;
import org.springframework.transaction.annotation.Transactional;

import static library_managment_system.controllers.helper.IndexController.sendSuccessMessage;



@RestController	
@RequestMapping(path="api/books")
public class BookController {
	@Autowired 
	private BookRepository bookRepository;

	@Transactional
	@PostMapping(path="") 
	public @ResponseBody Object addNewBook (@Valid @RequestBody BookValidator book ) {
		Book newBook = new Book();
		newBook.setTitle(book.getTitle());
        newBook.setIsbn(book.getIsbn());
        newBook.setAuthor(book.getAuthor());
        newBook.setPublicationYear(book.getPublicationYear());
		bookRepository.save(newBook);
		return sendSuccessMessage("book information have been saved successfully!");
	}

	@GetMapping(path="/{id}") 
	public @ResponseBody Object getBook (@PathVariable("id") long id) {
		Book book =  bookRepository.findById(id).orElseThrow(() -> new NoSuchBookExistsException(id));
		return book;
	}

	@Transactional
	@PutMapping(path="/{id}") 
	public @ResponseBody Object updateBook (@PathVariable("id") long id , @Valid @RequestBody BookValidator book) {
		Book updatedBook =  bookRepository.findById(id).orElseThrow(() -> new NoSuchBookExistsException(id));
		updatedBook.setTitle(book.getTitle());
        updatedBook.setIsbn(book.getIsbn());
        updatedBook.setAuthor(book.getAuthor());
        updatedBook.setPublicationYear(book.getPublicationYear());
		bookRepository.save(updatedBook);
		return sendSuccessMessage("book information has been updated successfully!");
	}

	@Transactional
	@DeleteMapping(path="/{id}") 
	public @ResponseBody Object deleteBook (@PathVariable("id") long id) {
		Book book =  bookRepository.findById(id).orElseThrow(() -> new NoSuchBookExistsException(id));
		bookRepository.delete(book);
		return sendSuccessMessage("book has been deleted successfully!");
	}

	@GetMapping(path="")
	public @ResponseBody Iterable<Book> getAllBooks() {
		return bookRepository.findAll();
	}

}