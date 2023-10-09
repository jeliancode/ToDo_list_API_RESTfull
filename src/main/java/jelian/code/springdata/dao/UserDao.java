package jelian.code.springdata.dao;

import java.util.Optional;
import jelian.code.springdata.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long> {

  Optional<User> findByUsername(String username);

}
