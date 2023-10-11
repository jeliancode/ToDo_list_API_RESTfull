package jelian.code.springdata.service;

import java.util.List;
import jelian.code.springdata.domain.Category;
import org.springframework.http.ResponseEntity;

public interface CategoryService {
  List<Category> categoryList();
  ResponseEntity<Object> save(Category category);
  ResponseEntity<Object> delete(Long idCategory);
  Category findCategory(Long idCategory);
}
