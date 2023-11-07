package ru.ivanov.video_test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
class WorkerControllerTest {
  @Mock
  private WorkerService workerService;

  @InjectMocks
  private WorkerController workerController;

  private MockMvc mockMvc;
  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(workerController).build();
    objectMapper = new ObjectMapper();
  }

  @Test
  void sumOfSalaries() throws Exception {
    when(workerService.getSumOfSalariesOfAllWorkers()).thenReturn(100);
    mockMvc.perform(get("/api/worker/salary-sum"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$").value(100));
    verify(workerService, times(1)).getSumOfSalariesOfAllWorkers();
  }

  @Test
  void hireWorker() throws Exception {
    Worker worker = new Worker(1L, "John", 500);
    String workerJson = objectMapper.writeValueAsString(worker);
    mockMvc.perform(post("/api/worker/hire")
      .contentType(MediaType.APPLICATION_JSON)
      .content(workerJson))
      .andExpect(status().isCreated());
    verify(workerService, times(1)).hireWorker(worker);
  }

  @Test
  void getWorkerById() throws Exception {
    Worker worker = new Worker(1L, "John", 500);
    when(workerService.findWorkerById(1L)).thenReturn(worker);
    mockMvc.perform(get("/api/worker/{id}", 1L))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.id").value(1L))
      .andExpect(jsonPath("$.name").value("John"))
      .andExpect(jsonPath("$.salary").value(500));
    verify(workerService, times(1)).findWorkerById(1L);
  }
}
