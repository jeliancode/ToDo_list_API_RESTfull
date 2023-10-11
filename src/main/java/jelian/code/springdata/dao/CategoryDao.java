package jelian.code.springdata.dao;

import java.util.Optional;
import jelian.code.springdata.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryDao extends CrudRepository<Category, Long> {

  Optional<Category> findByCategoryName(String categoryName);

}