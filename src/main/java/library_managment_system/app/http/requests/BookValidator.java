package library_managment_system.app.http.requests;

import jakarta.validation.constraints.*;
import lombok.*;
import java.sql.Timestamp;


@NoArgsConstructor
@AllArgsConstructor

@Data
public class BookValidator {



  @NotNull(message="book title is required")
  private String title;


  @NotNull(message="author name is required")
  private String author;


  @NotNull(message="publication Year is required")
  @Size(max=4 , message="publication year contains 4 digits only")
  @Pattern(regexp="^[0-9]+$", message="publication year contains  only digits")
  private String publicationYear;


  @NotNull(message="isbn is required")
  private String isbn;

}


