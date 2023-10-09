package jelian.code.springdata.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
@Entity
@Table(name = "task")
public class Task implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idTask;
  @Column(unique = true)
  @NotBlank
  private String taskName;
  @NotBlank
  private String details;
  @NotBlank
  private String startDate;
  @NotBlank
  private String dueDate;
  @NotBlank
  private String taskStatus;
  @NotNull
  private int taskPriority;
  @NotNull
  private int categoryIdCategory;
  @NotNull
  private int userIdUser;

}
