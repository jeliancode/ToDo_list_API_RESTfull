package jelian.code.springdata.repository;

import jelian.code.springdata.domain.User;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  User findByUsernameAndUserPasswordAndUserEnable(String username, String password,
      Boolean enabled);

  @Procedure
  void checkIn(@Param("user_username") String username,
      @Param("user_userPassword") String password);

  @Procedure
  int checkNotUniqueUsername(@Param("posible_username") String username);
}