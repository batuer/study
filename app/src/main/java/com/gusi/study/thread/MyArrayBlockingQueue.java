package com.gusi.study.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 作者：${ylw} on 2017-06-21 16:57
 */
public class MyArrayBlockingQueue<T> {
  private final T[] items;
  private Lock mLock = new ReentrantLock();//显示锁(1.获取释放的灵活性2.轮训锁和定时锁3.公平性)
  private Condition notFull = mLock.newCondition();
  private Condition notEmpty = mLock.newCondition();
  private int mHead;//头部索引
  private int tail;//尾部索引
  private int count;//总数

  public MyArrayBlockingQueue(int maxSize) {
    this.items = (T[]) new Object[maxSize];
  }

  public MyArrayBlockingQueue() {
    this(10);
  }

  public void put(T t) {
    mLock.lock();
    try {
      while (count == getCapacity()) {
        notFull.await();
      }
      items[tail] = t;
      if (++tail == getCapacity()) {
        tail = 0;
      }
      ++count;
      notEmpty.signalAll();
    } catch (Exception e) {

    } finally {
      //TODO  Important
      mLock.unlock();
    }
  }

  public T take() throws InterruptedException {
    mLock.lock();
    try {
      while (count == 0) {
        notEmpty.await();
      }
      T item = items[mHead];
      if (++mHead == getCapacity()) {
        mHead = 0;
      }
      --count;
      notFull.signalAll();
      return item;
    } finally {
      mLock.unlock();
    }
  }

  public int getCapacity() {
    return items.length;
  }

  public int size() {
    mLock.lock();
    try {
      return count;
    } finally {
      mLock.unlock();
    }
  }
}
