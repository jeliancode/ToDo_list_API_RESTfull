package jelian.code.springdata.service;

import java.util.HashMap;
import java.util.List;
import jelian.code.springdata.dao.UserDao;
import jelian.code.springdata.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImplementation implements UserService {

  @Autowired
  private UserDao userDao;
  private HashMap<String, Object> logMap;

  @Override
  @Transactional(readOnly = true)
  public List<User> userList() {
    return (List<User>) userDao.findAll();
  }

  @Override
  @Transactional
  public ResponseEntity<Object> save(User user) {
    var searchUser = userDao.findByUsername(user.getUsername());
   // int uniqueCount = userDao.checkNotUniqueUsername(user.getUsername());
    logMap = new HashMap<>();

    if (searchUser.isPresent() && user.getIdUser() == null) {
      logMap.put("ERROR", true);
      logMap.put("MESSAGE", "THIS USER EXISTS");
      return new ResponseEntity<>(
          logMap,
          HttpStatus.CONFLICT
      );
    }

    if (user.getIdUser() != null) {
      logMap.put("MESSAGE", "UPDATED");
    }

    userDao.save(user);
    logMap.put("DATA", user);
    logMap.put("MESSAGE", "SAVED");
    return new ResponseEntity<>(
        logMap,
        HttpStatus.CREATED
    );
  }

  @Override
  @Transactional
  public ResponseEntity<Object> delete(Long idUser) {
    var userExisting = userDao.existsById(idUser);
    logMap = new HashMap<>();
    if (!userExisting) {
      logMap.put("ERROR", true);
      logMap.put("MESSAGE", "THIS USER DON'T EXISTS");
      return new ResponseEntity<>(
          logMap,
          HttpStatus.NOT_FOUND
      );
    }
    userDao.deleteById(idUser);
    logMap.put("MESSAGE", "USER DELETED");
    return new ResponseEntity<>(
        logMap,
        HttpStatus.ACCEPTED
    );
  }

  @Override
  @Transactional(readOnly = true)
  public User findUser(Long idUser) {
    return userDao.findById(idUser).orElse(null);
  }
}