package jelian.code.springdata.controller;

import jakarta.validation.Valid;
import jelian.code.springdata.domain.Category;
import jelian.code.springdata.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @GetMapping
  public ResponseEntity<Iterable<Category>> getAllCategories() {
    Iterable<Category> categories = categoryService.categoryList();
    return ResponseEntity.ok(categories);
  }

  @PostMapping("/createCategory")
  public ResponseEntity createCategory(@Valid @RequestBody Category category, Errors errors) {
    if (errors.hasErrors()) {
      return ResponseEntity.badRequest().build();
    }
    return categoryService.save(category);
  }

  @PostMapping("/editCategory/{idCategory}")
  public ResponseEntity updateCategory(@PathVariable("idCategory") Long idCategory, @Valid @RequestBody Category updatedCategory, Errors errors){
    if (errors.hasErrors()) {
      return ResponseEntity.badRequest().build();
    }
    updatedCategory.setIdCategory(idCategory);
    return categoryService.save(updatedCategory);
  }

  @DeleteMapping("/deleteCategory/{idCategory}")
  public ResponseEntity<Object> deleteCategory(@PathVariable("idCategory") Long idCategory){
    return categoryService.delete(idCategory);
  }
}