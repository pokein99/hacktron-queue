package com.dbs.hacktron.service;

import org.springframework.stereotype.Service;

import com.dbs.hacktron.exception.QueueException;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/***
 * 
 * @author uday kiran
 * @apiNote Vaibhav Mehta
 *
 */

@Service
public class QueueService {

    private static final Map<String, Queue<String>> queues = new HashMap<>();

    public Queue<String> createQueue(String queueName, Integer maxLimit)throws QueueException {
        if (queues.containsKey(queueName)) {
        	throw new QueueException(queueName+" is already exits");
           // return queues.get(queueName);
        }
        Queue<String> queue = new ArrayBlockingQueue<>(maxLimit);
        queues.put(queueName, queue);
        return queue;
    }

    public void clearQueue(String queueName) throws QueueException{
        if (queues.containsKey(queueName)) {
            queues.put(queueName, new LinkedList<>());
        }else {
        	throw new QueueException(queueName + " doesn't exits");
        }
    }

    public void add(String queueName, String value) throws QueueException {
        Queue<String> queue = queues.getOrDefault(queueName, new LinkedList<>());
        queue.add(value);
        queues.put(queueName, queue);
    }

   public String remove(String queueName) throws QueueException{
      Queue<String> status = queues.remove(queueName);
      if(!status.isEmpty()) {
    	  return queueName+" Successfully deleted";
      }
      else {
    	  throw new QueueException(queueName + " doesn't exits");
      }
   }

   public String removeElement(String queueName) throws QueueException{
      Queue<String> queue = queues.get(queueName);
      String status = queue.remove();
      if(status ==null) {
    	  throw new QueueException(queueName + " doesn't exits");
      }
      else {
    	  return status+" message removed Successfully";
      }
   }

public static Map<String, Queue<String>> getQueues() {
	return queues;
}

}
