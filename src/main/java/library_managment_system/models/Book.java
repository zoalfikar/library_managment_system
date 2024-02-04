package library_managment_system.models;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import lombok.*;
import library_managment_system.models.Book;
import library_managment_system.models.BorrowingRecord;
import com.fasterxml.jackson.annotation.JsonIgnore;



@NoArgsConstructor
@RequiredArgsConstructor

@Entity 
@Table(name = "books")
@Data
public class Book {
    
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;


  @NonNull
  @Column(name = "title", nullable=false)
  private String title;


  @NonNull
  @Column(name = "author" , nullable=false)
  private String author;


  @NonNull
  @Column(name = "publication_year" , nullable=false)
  private String publicationYear;


  @NonNull
  @Column(name = "isbn" ,unique=true , nullable=false)
  private String isbn;

  @Column(name = "created_at" ,columnDefinition = "TIMESTAMP NOT NULL DEFAULT NOW()")
  private Timestamp createdAt;


  @Column(name = "updated_at",columnDefinition = "TIMESTAMP NOT NULL DEFAULT NOW() ON UPDATE now()")
  private Timestamp updatedAt;


  @OneToMany(fetch = FetchType.LAZY, mappedBy="book" , cascade = CascadeType.ALL)
  @JsonIgnore
  private List<BorrowingRecord> borrowingRecords;

}


