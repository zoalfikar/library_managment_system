package library_managment_system.repositories;
import org.springframework.data.repository.CrudRepository;

import library_managment_system.models.Patron;


public interface PatronRepository extends CrudRepository<Patron, Long> {

}