package jelian.code.springdata.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
@Entity
@Table(name = "category")
public class Category {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idCategory;
  @NotBlank
  private String categoryName;
}
