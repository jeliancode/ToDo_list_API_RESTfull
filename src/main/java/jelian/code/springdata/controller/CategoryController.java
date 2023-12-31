package jelian.code.springdata.controller;

import jakarta.validation.Valid;
import jelian.code.springdata.domain.Category;
import jelian.code.springdata.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("api/categories")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;
  @CrossOrigin(origins = "http://127.0.0.1:5173")
  @GetMapping
  public ResponseEntity<Iterable<Category>> getAllCategories() {
    Iterable<Category> categories = categoryService.categoryList();
    return ResponseEntity.ok(categories);
  }

  @GetMapping("/getCategory/{idCategory}")
  public ResponseEntity getCategory(@PathVariable("idCategory") Long idCategory) {
    var category = categoryService.findCategory(idCategory);
    return ResponseEntity.ok(category);
  }
}
