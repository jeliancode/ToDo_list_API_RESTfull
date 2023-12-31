package jelian.code.springdata.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Entity
@Table(name = "user")
public class User implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idUser;
  @NotBlank
  @Column(unique = true)
  private String username;
  @Email
  @NotBlank
  @Column(unique = true)
  private String email;
  @Size(min =  8, max = 12)
  private String userPassword;
  @NotBlank
  private String firstname;
  @NotBlank
  private String lastname;
  private boolean userEnable;
}

