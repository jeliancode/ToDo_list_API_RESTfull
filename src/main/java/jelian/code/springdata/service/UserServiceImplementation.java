package jelian.code.springdata.service;

import java.util.List;
import jelian.code.springdata.dao.UserDao;
import jelian.code.springdata.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImplementation implements UserService {

  @Autowired
  private UserDao userDao;

  @Override
  @Transactional(readOnly = true)
  public List<User> userList() {
    return (List<User>) userDao.findAll();
  }

  @Override
  @Transactional
  public ResponseEntity save(User user) {
    userDao.save(user);
    return null;
  }

  @Override
  @Transactional
  public ResponseEntity<Object> delete(Long idUser) {
    userDao.deleteById(idUser);
    return null;
  }

  @Override
  @Transactional(readOnly = true)
  public User findUser(User user) {
    return userDao.findById(user.getIdUser()).orElse(null);
  }
}
