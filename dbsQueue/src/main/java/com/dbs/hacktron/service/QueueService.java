package com.dbs.hacktron.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/***
 * 
 * @author uday kiran
 * @apiNote Vaibhav Mehta
 *
 */

@Service
public class QueueService {

    private static final Map<String, Queue<String>> queues = new HashMap<>();

    public Queue<String> createQueue(String queueName) {
        if (queues.containsKey(queueName)) {
            return queues.get(queueName);
        }
        //Queue<String> waitingCustomers = new ArrayBlockingQueue<>(200); -- fixed size queue
        Queue<String> queue = new LinkedList<>();
        queues.put(queueName, queue);
        return queue;
    }

    public void clearQueue(String queueName) {
        if (queues.containsKey(queueName)) {
            queues.put(queueName, new LinkedList<>());
        }
    }

    public void add(String queueName, String value) {
        Queue<String> queue = queues.getOrDefault(queueName, new LinkedList<>());
        queue.add(value);
        queues.put(queueName, queue);
    }

   public void remove(String queueName) {
      queues.remove(queueName);
   }

   public void remove(String queueName, String value) {
      Queue<String> queue = queues.get(queueName);
      queue.remove(value);
   }

public static Map<String, Queue<String>> getQueues() {
	return queues;
}

}
