package ru.ivanov.video_test;

public class WorkerNotFoundException extends RuntimeException {
  public WorkerNotFoundException(String message) {
    super(message);
  }
}
