package jelian.code.springdata.dao;

import java.util.Optional;
import jelian.code.springdata.domain.User;
import jelian.code.springdata.repository.UserRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long>, UserRepository {

  Optional<User> findByUsername(String username);
}
