package com.dbs.hacktron.controller;

import java.util.Map;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.hacktron.exception.QueueException;
import com.dbs.hacktron.service.QueueService;

@CrossOrigin(origins = "*")
@RestController("/dbs")
public class QueueController {
	
	@Autowired
	QueueService queueService; 
	
	/***
	 * create new queue
	 * @param queueName
	 * @return
	 * @throws QueueException 
	 */
	@PostMapping("createQueue/{queueName}/{queueLimit}")
	public Queue<String> createQueue(@PathVariable String queueName, @PathVariable Integer queueLimit) throws QueueException {
		return queueService.createQueue(queueName, queueLimit);
	}
	
	/***
	 * fetch contents of all queues
	 * @return
	 */
	@GetMapping("getQueue")
	public Map<String, Queue<String>> getQueue(){
		return queueService.getQueues();
	}
	
	/***
	 * add element to queue
	 * @param queueName
	 * @param queueValue
	 * @return
	 * @throws QueueException 
	 */
	@PostMapping("addToQueue/{queueName}/{queueValue}")
	public ResponseEntity<Object> addQueue(@PathVariable String queueName, @PathVariable String queueValue)  {
		try {
			queueService.add(queueName,queueValue);
		} catch (QueueException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(queueService.getQueues(), HttpStatus.OK);
	}
	
	/***
	 * clear contents of queue
	 * @param queueName
	 * @return
	 * @throws QueueException 
	 */
	@DeleteMapping("deleteAllQueueElements/{queueName}")
	public ResponseEntity<String> deleteAllQueueElements(@PathVariable String queueName) {
		try {
			queueService.clearQueue(queueName);
		} catch (QueueException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity("ALL Elements from "+queueName+" deleted",HttpStatus.OK);
	}
	
	/***
	 * delete queue
	 * @param queueName
	 * @return
	 * @throws QueueException 
	 */
	@DeleteMapping("deleteQueue/{queueName}")
	public ResponseEntity<String> deleteQueue(@PathVariable String queueName) {
		try {
			queueService.remove(queueName);
		} catch (QueueException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity("DELETED queue: "+queueName,HttpStatus.OK);
	}
	
	/***
	 * remove element from queue
	 * @param queueName
	 * @param queueValue
	 * @return
	 * @throws QueueException 
	 */
	@DeleteMapping("deleteQueueValue/{queueName}")
	public ResponseEntity<String> deleteQueueValue(@PathVariable String queueName) {
		 String response;
		try {
			response = queueService.removeElement(queueName);
		} catch (QueueException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(response,HttpStatus.OK);
	}
	

}
