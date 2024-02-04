package library_managment_system.app.http.requests;

import jakarta.validation.constraints.*;
import lombok.*;



@NoArgsConstructor
@AllArgsConstructor

@Data
public class PatronValidator {


  @NotNull(message="first name is required")
  private String firstName;


  @NotNull(message="last name is required")
  private String lastName;


  @Min(value=10, message="the user should be older than 10 years")
  @NotNull(message="age is required")
  private Integer age;


  @NotNull(message="phone number is required")
  @Pattern(regexp="^[0-9]+$", message="the phone number contains  only digits")
  private String phoneNumber;


  @NotNull(message="email  is required")
  @Email(message="invalid email")
  private String email;
}


