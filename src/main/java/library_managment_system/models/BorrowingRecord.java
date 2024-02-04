package library_managment_system.models;
import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.*;
import library_managment_system.models.Book;
import library_managment_system.models.Patron;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;
@NoArgsConstructor
@RequiredArgsConstructor

@Entity 
@Table(name = "borrowing_records")
@Data
public class BorrowingRecord {
    
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  @NonNull
  @Column(name = "patron_id", nullable=false)
  private Long patronId;


  @NonNull
  @Column(name = "book_id" ,nullable=false)
  private Long bookId;


  @NonNull
  @Column(name = "borrwing_date" ,columnDefinition = "TIMESTAMP NOT NULL DEFAULT NOW()")
  private Timestamp borrwingDate;


  @NonNull
  @Column(name = "returned_date" ,nullable=true)
  private Timestamp returnedDate;


  @Column(name = "created_at" ,columnDefinition = "TIMESTAMP NOT NULL DEFAULT NOW()")
  private Timestamp createdAt;


  @Column(name = "updated_at",columnDefinition = "TIMESTAMP NOT NULL DEFAULT NOW() ON UPDATE now()")
  private Timestamp updatedAt;


  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="book_id",referencedColumnName="id" ,insertable=false, updatable=false)
  @JsonIgnore
    private Book book ;

    
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="patron_id",referencedColumnName="id" ,insertable=false, updatable=false)
  @JsonIgnore
    private Patron patron ;

}


