package com.example.demo.consumersupplier;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class CustomBlockingQueueTest {

    @Test
    public void testSlowProducingLockCondition() throws InterruptedException {
        CustomBlockingQueueLockCondition customBlockingQueue = new CustomBlockingQueueLockCondition<String>(2);
        Thread producer = new Thread(new Producer(customBlockingQueue, 100));
        Thread consumer = new Thread(new Consumer(customBlockingQueue, 10));
        startJoinProducerConsumer(producer, consumer);
    }

    @Test
    public void testSlowConsumingLockCondition() throws InterruptedException {
        CustomBlockingQueueLockCondition customBlockingQueue = new CustomBlockingQueueLockCondition<String>(2);
        Thread producer = new Thread(new Producer(customBlockingQueue, 10));
        Thread consumer = new Thread(new Consumer(customBlockingQueue, 100));
        startJoinProducerConsumer(producer, consumer);
    }

    @Test
    public void testSlowProducingSemaphore() throws InterruptedException {
        CustomBlockingQueueLockCondition customBlockingQueue = new CustomBlockingQueueLockCondition<String>(2);
        Thread producer = new Thread(new Producer(customBlockingQueue, 100));
        Thread consumer = new Thread(new Consumer(customBlockingQueue, 10));
        startJoinProducerConsumer(producer, consumer);
    }

    @Test
    public void testSlowConsumingSemaphore() throws InterruptedException {
        CustomBlockingQueueLockCondition customBlockingQueue = new CustomBlockingQueueLockCondition<String>(2);
        Thread producer = new Thread(new Producer(customBlockingQueue, 10));
        Thread consumer = new Thread(new Consumer(customBlockingQueue, 100));
        startJoinProducerConsumer(producer, consumer);
    }

    private void startJoinProducerConsumer(Thread producer, Thread consumer) throws InterruptedException {
        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
    }
}

class Producer implements Runnable {

    private CustomBlockingQueueLockCondition customBlockingQueue;
    private int sleepTime;

    public Producer(CustomBlockingQueueLockCondition customBlockingQueue, int sleepTime) {
        this.customBlockingQueue = customBlockingQueue;
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            try {
                customBlockingQueue.put(i);
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class Consumer implements Runnable {
    private CustomBlockingQueueLockCondition customBlockingQueue;
    private int sleepTime;

    public Consumer(CustomBlockingQueueLockCondition customBlockingQueue, int sleepTime) {
        this.customBlockingQueue = customBlockingQueue;
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            try {
                customBlockingQueue.get();
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
