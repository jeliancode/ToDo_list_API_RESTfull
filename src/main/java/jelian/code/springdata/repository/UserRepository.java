package jelian.code.springdata.repository;

import jelian.code.springdata.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndUserPasswordAndUserEnable(String username, String password, Boolean enabled);

}
