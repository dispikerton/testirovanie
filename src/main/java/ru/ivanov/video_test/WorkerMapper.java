package ru.ivanov.video_test;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkerMapper implements RowMapper<Worker> {

  @Override
  public Worker mapRow(ResultSet rs, int rowNum) throws SQLException {
    long id = rs.getLong("id");
    String name = rs.getString("name");
    int salary = rs.getInt("salary");
    return new Worker(id, name, salary);
  }
}
