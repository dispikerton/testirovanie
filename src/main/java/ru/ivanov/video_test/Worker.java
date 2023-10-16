package ru.ivanov.video_test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Worker {
  private long id;
  private String name;
  private int salary;
}
