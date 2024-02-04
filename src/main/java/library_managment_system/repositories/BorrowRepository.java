package library_managment_system.repositories;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import library_managment_system.models.BorrowingRecord;


public interface BorrowRepository extends CrudRepository<BorrowingRecord, Long> {

    BorrowingRecord findByBookIdAndPatronIdAndReturnedDateIsNull(Long bookId, Long patronId);
    BorrowingRecord findByBookIdAndReturnedDateIsNull(Long bookId);
}