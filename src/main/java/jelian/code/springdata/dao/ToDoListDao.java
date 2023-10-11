package jelian.code.springdata.dao;

import java.util.Optional;
import jelian.code.springdata.domain.Task;
import org.springframework.data.repository.CrudRepository;

public interface ToDoListDao extends CrudRepository<Task, Long> {

  Optional<Task> findByTaskName(String taskName);
}