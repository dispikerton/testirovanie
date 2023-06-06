package ru.ivanov.video_test;

import lombok.RequiredArgsConstructor;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class WorkerRepository {

  private final NamedParameterJdbcTemplate jdbcTemplate;

  public List<Worker> getWorkers() {
    return jdbcTemplate.query("SELECT * FROM worker", rs -> {
      List<Worker> workers = new ArrayList<>();
      while (rs.next()) {
        long id = rs.getLong("id");
        String name = rs.getString("name");
        int salary = rs.getInt("salary");

        Worker worker = new Worker(id, name, salary);
        workers.add(worker);
      }
      return workers;
    });
  }
}
