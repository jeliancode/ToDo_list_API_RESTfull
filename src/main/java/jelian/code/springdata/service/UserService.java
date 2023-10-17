package jelian.code.springdata.service;

import java.util.List;
import jelian.code.springdata.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {
  List<User> userList();
  ResponseEntity save(User user);
  ResponseEntity<Object> delete(Long idUser);
  User findUser(Long idUser);
}
