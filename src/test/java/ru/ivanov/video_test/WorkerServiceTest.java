package ru.ivanov.video_test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class WorkerServiceTest {
  @InjectMocks
  private WorkerService workerService;
  @Mock
  private WorkerRepository workerRepository;

  @Test
  void sumOfSalaries() {
    Worker worker1 = new Worker(1, "Дмитрий", 100_000);
    Worker worker2 = new Worker(1, "Дмитрий", 150_000);
    Mockito.when(workerRepository.findAll()).thenReturn(List.of(worker1, worker2));

    int sumOfSalariesOfAllWorkers = workerService.getSumOfSalariesOfAllWorkers();
    Assertions.assertEquals(250000, sumOfSalariesOfAllWorkers);
  }

  @Test
  void hire_withLowSalary_throwsException() {
    Worker worker = new Worker(1, "Алексей", 15_000);
    assertThrows(TooLowSalaryException.class, () -> workerService.hireWorker(worker));
  }

  @Test
  void hire_withValidSalary_savesWorker() {
    Worker worker = new Worker(1, "Иван", 20_000);
    workerService.hireWorker(worker);
    Mockito.verify(workerRepository, Mockito.times(1)).save(worker);
  }
}
