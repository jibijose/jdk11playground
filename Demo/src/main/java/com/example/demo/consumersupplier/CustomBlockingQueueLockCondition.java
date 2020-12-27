package com.example.demo.consumersupplier;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class CustomBlockingQueueLockCondition<E> {

    private Queue<E> queue;
    private int capacity;
    Lock lock = new ReentrantLock(true);
    Condition notEmpty = lock.newCondition();
    Condition notFull = lock.newCondition();

    public CustomBlockingQueueLockCondition(int capacity) {
        this.capacity = capacity;
        queue = new LinkedList<>();
    }

    public void put(E element) throws InterruptedException {
        log.debug("Putting: " + element);
        lock.lock();
        try {
            while(queue.size() >= capacity) {
                notFull.await();
            }
            queue.add(element);
            log.debug("Put: " + element);
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public E get() throws InterruptedException {
        log.debug("Getting: ");
        lock.lock();
        try {
            while(queue.size() <= 0 ) {
                notEmpty.await();
            }
            E element = queue.remove();
            log.debug("Got: " + element);
            notFull.signalAll();
            return element;
        } finally {
            lock.unlock();
        }
    }

}
