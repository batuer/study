package com.gusi.study.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 作者：${ylw} on 2017-06-21 17:12
 * 信号量
 */
public class SemaphoreTest {
  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(3);
    final Semaphore semaphore = new Semaphore(3);//信号量
    for (int i = 0; i < 5; i++) {
      executorService.submit(new Runnable() {
        @Override public void run() {
          try {
            semaphore.acquire();
            System.out.println("--------");
            Thread.sleep(3000);
            semaphore.release();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      });
    }
  }
}
