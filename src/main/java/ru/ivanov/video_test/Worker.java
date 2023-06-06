package ru.ivanov.video_test;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Worker {
  private long id;
  private String name;
  private int salary;
}
