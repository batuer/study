package com.gusi.study.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 作者：${ylw} on 2017-06-21 17:17
 * 允许一组线程互相等待 ，
 */
public class CyclicBarrierTest {
  private static final int SIZE = 5;
  private static CyclicBarrier sBarrier;

  public static void main(String[] args) {
    sBarrier = new CyclicBarrier(SIZE, new Runnable() {
      @Override public void run() {
        System.out.println("all over");
      }
    });
    for (int i = 0; i < 5; i++) {
      new WorkThread(sBarrier).start();
    }
    //重置继续使用
    //sBarrier.reset();
  }

  static class WorkThread extends Thread {
    private CyclicBarrier mBarrier;

    public WorkThread(CyclicBarrier barrier) {
      mBarrier = barrier;
    }

    @Override public void run() {
      //do sth
      try {
        mBarrier.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (BrokenBarrierException e) {
        e.printStackTrace();
      }
    }
  }
}
