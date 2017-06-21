package com.gusi.study.thread;

import java.util.concurrent.CountDownLatch;

/**
 * 作者：${ylw} on 2017-06-21 17:26
 * 允许一线程或多个线程一直等待,直到条件满足
 */
public class CountDownLatchTest {
  private static final int SIZE = 5;

  public static void main(String[] args) {
    CountDownLatch countDownLatch = new CountDownLatch(SIZE);
    for (int i = 0; i < 5; i++) {
      new WorkThread(countDownLatch).start();
    }

    try {
      //主线程等待多个任务完成
      countDownLatch.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  static class WorkThread extends Thread {
    private CountDownLatch mLatch;

    public WorkThread(CountDownLatch latch) {

      mLatch = latch;
    }

    @Override public void run() {
      //do sth
      //CountDownLatch数值 -1
      mLatch.countDown();
    }
  }
}
