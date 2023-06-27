package ru.ivanov.video_test;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkerService {

  private final WorkerRepository workerRepository;

  public int getSumOfSalariesOfAllWorkers() {
    List<Worker> workers = workerRepository.getWorkers();
    return workers.stream()
      .mapToInt(Worker::getSalary)
      .sum();
  }

  public void hire(Worker worker) {
    if (worker.getSalary() < 16_242){
      throw new TooLowSalaryException();
    }
    workerRepository.saveWorker(worker);
  }
}
