package ru.ivanov.video_test;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkerService {

  private final WorkerRepository workerRepository;

  public int getSumOfSalariesOfAllWorkers() {
    List<Worker> workers = workerRepository.findAll();
    return workers.stream()
      .mapToInt(Worker::getSalary)
      .sum();
  }

  public void hireWorker(Worker worker) {
    if (worker.getSalary() < 16_242){
      throw new TooLowSalaryException();
    }
    workerRepository.save(worker);
  }

  public Worker findWorkerById(long id) {
    Optional<Worker> worker = workerRepository.findById(id);
    return worker.orElseThrow(() -> new WorkerNotFoundException("Worker not found"));
  }

  public void updateWorker(Worker updatedWorker) {
    Worker existingWorker = findWorkerById(updatedWorker.getId());
    existingWorker.setName(updatedWorker.getName());
    existingWorker.setSalary(updatedWorker.getSalary());
    workerRepository.update(existingWorker);
  }
}
