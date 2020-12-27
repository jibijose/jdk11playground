package com.example.demo.consumersupplier;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class CustomBlockingQueueSemaphore<E> {

    private Queue<E> queue;
    private int capacity;
    Semaphore consumerSemaphore = new Semaphore(capacity);
    Semaphore producerSemaphore = new Semaphore(capacity);

    public CustomBlockingQueueSemaphore(int capacity) {
        this.capacity = capacity;
        queue = new LinkedList<>();
    }

    public void put(E element) throws InterruptedException {
        log.debug("Putting: " + element);
        producerSemaphore.acquire();
        queue.add(element);
        log.debug("Put: " + element);
        consumerSemaphore.release();
    }

    public E get() throws InterruptedException {
        log.debug("Getting: ");
        consumerSemaphore.acquire();
        E element = queue.remove();
        log.debug("Got: " + element);
        producerSemaphore.release();
        return element;
    }

}
