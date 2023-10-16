package ru.ivanov.video_test;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/worker")
@RequiredArgsConstructor
public class WorkerController {

  private final WorkerService workerService;

  @GetMapping("/salary-sum")
  public ResponseEntity<Integer> getSumOfSalariesOfAllWorkers() {
    int sum = workerService.getSumOfSalariesOfAllWorkers();
    return new ResponseEntity<>(sum, HttpStatus.OK);
  }

  @PostMapping("/hire")
  public ResponseEntity<Void> hire(@RequestBody Worker worker) {
    workerService.hireWorker(worker);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Worker> getWorkerById(@PathVariable long id) {
    Worker worker = workerService.findWorkerById(id);
    return new ResponseEntity<>(worker, HttpStatus.OK);
  }

  @PutMapping("/update")
  public ResponseEntity<Void> updateWorker(@RequestBody Worker updatedWorker) {
    workerService.updateWorker(updatedWorker);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
