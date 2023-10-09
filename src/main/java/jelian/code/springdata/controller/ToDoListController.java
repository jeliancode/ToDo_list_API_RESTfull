package jelian.code.springdata.controller;

import jakarta.validation.Valid;
import jelian.code.springdata.domain.Task;
import jelian.code.springdata.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/tasks")
@Slf4j
public class ToDoListController {

  @Autowired
  private TaskService taskService;

  @GetMapping
  public ResponseEntity<Iterable<Task>> getAllTasks() {
    Iterable<Task> tasks = taskService.taskList();
    return ResponseEntity.ok(tasks);
  }

  @PostMapping("/createTask")
  public ResponseEntity createTask(@Valid @RequestBody Task task, Errors errors) {
    if (errors.hasErrors()) {
      return ResponseEntity.badRequest().build();
    }
    return taskService.save(task);
  }

  @PostMapping("/updateTask/{idTask}")
  public ResponseEntity updateTask(@PathVariable("idTask") Long idTask,
      @Valid @RequestBody Task updatedTask, Errors errors) {
    if (errors.hasErrors()) {
      return ResponseEntity.badRequest().build();
    }
    updatedTask.setIdTask(idTask);
    return taskService.save(updatedTask);
  }

  @DeleteMapping("/deleteTask/{idTask}")
  public ResponseEntity<Object> deleteTask(@PathVariable("idTask") Long idTask) {
    return taskService.delete(idTask);
  }
}