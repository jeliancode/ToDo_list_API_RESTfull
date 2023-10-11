package jelian.code.springdata.service;

import java.util.HashMap;
import java.util.List;
import jelian.code.springdata.dao.CategoryDao;
import jelian.code.springdata.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryServiceImplementation implements CategoryService {

  @Autowired
  private CategoryDao categoryDao;
  private HashMap<String, Object> logMap;

  @Override
  @Transactional(readOnly = true)
  public List<Category> categoryList() {
    return (List<Category>) categoryDao.findAll();
  }

  @Override
  @Transactional
  public ResponseEntity<Object> save(Category category) {
    var searchCategory = categoryDao.findByCategoryName(category.getCategoryName());
    logMap = new HashMap<>();

    if (searchCategory.isPresent() && category.getIdCategory() == null) {
      logMap.put("ERROR", true);
      logMap.put("ERROR", "THIS CATEGORY EXISTS");
      return new ResponseEntity<>(
          logMap,
          HttpStatus.CONFLICT
      );
    }

    if (category.getIdCategory() != null) {
      logMap.put("MESSAGE", "UPDATED");
    }
    categoryDao.save(category);
    logMap.put("DATA", category);
    logMap.put("MESSAGE", "SAVED");
    return new ResponseEntity<>(
        logMap,
        HttpStatus.CREATED
    );
  }

  @Override
  public ResponseEntity<Object> delete(Long idCategory) {
    var categoryExisting = categoryDao.existsById(idCategory);
    logMap = new HashMap<>();
    if (!categoryExisting) {
      logMap.put("ERROR", true);
      logMap.put("MESSAGE", "THIS CATEGORY NOT EXISTS");
      return new ResponseEntity<>(
          logMap,
          HttpStatus.NOT_FOUND
      );
    }
    categoryDao.deleteById(idCategory);
    logMap.put("MESSAGE", "CATEGORY DELETED");
    return new ResponseEntity<>(
        logMap,
        HttpStatus.ACCEPTED
    );
  }

  @Override
  public Category findCategory(Long idCategory) {
    return categoryDao.findById(idCategory).orElse(null);
  }
}