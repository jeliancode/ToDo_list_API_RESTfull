package jelian.code.springdata.controller;

import jakarta.validation.Valid;
import jelian.code.springdata.domain.User;
import jelian.code.springdata.service.UserService;
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
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping
  public ResponseEntity<Iterable<User>> getAllUsers() {
    Iterable<User> users = userService.userList();
    return ResponseEntity.ok(users);
  }

  @PostMapping("/createUser")
  public ResponseEntity createUser(@Valid @RequestBody User user, Errors errors) {
    if (errors.hasErrors()) {
      return ResponseEntity.badRequest().build();
    }
    return userService.save(user);
  }
  @PostMapping("/editUser/{idUser}")
  public ResponseEntity updateUser(@PathVariable("idUser") Long idUser, @Valid @RequestBody User updatedUser, Errors errors) {
    if (errors.hasErrors()) {
      return ResponseEntity.badRequest().build();
    }
    updatedUser.setIdUser(idUser);
    return userService.save(updatedUser);
  }

  @DeleteMapping("/deleteUser/{idUser}")
  public ResponseEntity<Object> deleteTask(@PathVariable("idUser") Long idUser) {
    return userService.delete(idUser);
  }
}