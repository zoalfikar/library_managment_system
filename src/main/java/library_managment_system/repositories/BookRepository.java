package library_managment_system.repositories;
import org.springframework.data.repository.CrudRepository;

import library_managment_system.models.Book;


public interface BookRepository extends CrudRepository<Book, Long> {

}