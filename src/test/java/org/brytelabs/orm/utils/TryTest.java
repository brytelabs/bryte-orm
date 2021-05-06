package org.brytelabs.orm.utils;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class TryTest {

  @Test
  public void tryCatchIgnoreException() {
    ExecutorService executorService = Executors.newFixedThreadPool(3);
    Lazy<Double> lazy = Lazy.of(Math::random);

    long current = System.currentTimeMillis();
    List<Future<Double>> futures = IntStream.range(0, 100)
        .mapToObj(i -> executorService.submit(lazy::get))
        .collect(Collectors.toList());

    List<Double> distinct = futures.stream()
        .map(this::getFromFuture)
        .distinct()
        .collect(Collectors.toList());
    long timeTaken = System.currentTimeMillis() - current;
    System.out.println("timeTaken = " + timeTaken);
    assertEquals(1, distinct.size());
  }

  private Double getFromFuture(Future<Double> future) {
    try {
      return future.get();
    } catch (Exception e) {
      throw new RuntimeException("Some exception", e);
    }
  }
}
