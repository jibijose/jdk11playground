package com.example.demo.consumersupplier;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class CustomBlockingQueueTest {

    @Test
    public void testBlockCallAboveCapacity() throws InterruptedException {
        CustomBlockingQueueLockCondition customBlockingQueue = new CustomBlockingQueueLockCondition<String>(2);
        customBlockingQueue.put("jibi1");
        customBlockingQueue.put("jose1");
        customBlockingQueue.get();
        customBlockingQueue.put("jose2");

        customBlockingQueue.put("block");
    }
}
