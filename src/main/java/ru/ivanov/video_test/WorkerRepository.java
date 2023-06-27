package ru.ivanov.video_test;

import lombok.RequiredArgsConstructor;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
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

  public void saveWorker(Worker worker) {
    String sql = "INSERT INTO worker (name, salary) VALUES (:name, :salary)";

    SqlParameterSource parameters = new MapSqlParameterSource()
      .addValue("name", worker.getName())
      .addValue("salary", worker.getSalary());
    jdbcTemplate.update(sql, parameters);
  }
}
