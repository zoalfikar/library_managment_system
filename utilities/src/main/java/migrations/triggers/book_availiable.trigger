CREATE TRIGGER book_availiable BEFORE  INSERT ON borrowing_records FOR EACH ROW
BEGIN
DECLARE date TIMESTAMP;
DECLARE id INT(11);
select returned_date , book_id INTO date , id from borrowing_records where book_id = NEW.book_id LIMIT 1 ;
IF (date IS  NULL AND id IS NOT NULL) THEN
   SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'BOOK WITH NOT AVAILABLE RIGHT NOW';
END IF;
END;