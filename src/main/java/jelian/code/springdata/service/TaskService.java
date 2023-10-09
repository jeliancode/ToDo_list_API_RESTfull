package jelian.code.springdata.service;

import java.util.List;
import jelian.code.springdata.domain.Task;
import org.springframework.http.ResponseEntity;

public interface TaskService {
  List<Task> taskList();
  ResponseEntity<Object> save(Task task);
  ResponseEntity<Object> delete(Long idTask);
  Task findTask(Long idTask);
}
