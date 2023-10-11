package jelian.code.springdata.service;

import java.util.HashMap;
import java.util.List;
import jelian.code.springdata.dao.ToDoListDao;
import jelian.code.springdata.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskServiceImplementation implements TaskService {

  @Autowired
  private ToDoListDao toDoListDao;
  private HashMap<String, Object> logMap;

  @Override
  @Transactional(readOnly = true)
  public List<Task> taskList() {
    return (List<Task>) toDoListDao.findAll();
  }

  @Override
  @Transactional
  public ResponseEntity<Object> save(Task task) {
    var searchResult = toDoListDao.findByTaskName(task.getTaskName());
    logMap = new HashMap<>();

    if (searchResult.isPresent() && task.getIdTask() == null) {
      logMap.put("ERROR", true);
      logMap.put("MESSAGE", "THIS TASK EXISTS");
      return new ResponseEntity<>(
          logMap,
          HttpStatus.CONFLICT
      );
    }

    if (task.getIdTask() != null) {
      logMap.put("MESSAGE", "UPDATED");
    }

    toDoListDao.save(task);
    logMap.put("DATA", task);
    logMap.put("MESSAGE", "SAVED");
    return new ResponseEntity<>(
        logMap,
        HttpStatus.CREATED
    );
  }


  @Override
  @Transactional
  public ResponseEntity<Object> delete(Long idTask) {
    var taskExisting = toDoListDao.existsById(idTask);
    logMap = new HashMap<>();
    if (!taskExisting) {
      logMap.put("ERROR", true);
      logMap.put("MESSAGE", "THIS TASK NOT EXISTS");
      return new ResponseEntity<>(
          logMap,
          HttpStatus.NOT_FOUND
      );
    }
    toDoListDao.deleteById(idTask);
    logMap.put("MESSAGE", "TASK DELETED");
    return new ResponseEntity<>(
        logMap,
        HttpStatus.ACCEPTED
    );
  }

  @Override
  @Transactional(readOnly = true)
  public Task findTask(Long idTask) {
    return toDoListDao.findById(idTask).orElse(null);
  }
}
