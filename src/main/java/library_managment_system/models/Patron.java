package library_managment_system.models;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;
import library_managment_system.models.BorrowingRecord;


@NoArgsConstructor
@RequiredArgsConstructor

@Entity 
@Table(name = "patrons")
@Data

public class Patron {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  @NonNull
  @Column(name = "first_name" , nullable=false)
  private String firstName;

  @NonNull
  @Column(name = "last_name" , nullable=false)
  private String lastName;

  @NonNull
  @Column(name = "age", nullable=false)
  private Integer age;

  @NonNull
  @Column(name = "phone_number" , nullable=false)
  private String phoneNumber;

  @NonNull
  @Column(name = "email" ,unique=true , nullable=false)
  private String email;

  @Column(name = "created_at" ,columnDefinition = "TIMESTAMP NOT NULL DEFAULT NOW()")
  private Timestamp createdAt;


  @Column(name = "updated_at",columnDefinition = "TIMESTAMP NOT NULL DEFAULT NOW() ON UPDATE now()")
  private Timestamp updatedAt;


  @OneToMany(fetch = FetchType.LAZY, mappedBy="patron" , cascade = CascadeType.ALL)
  @JsonIgnore
  private List<BorrowingRecord> borrowingRecords;


  public String name() {
    return this.firstName + " " +this.lastName;
}

}


