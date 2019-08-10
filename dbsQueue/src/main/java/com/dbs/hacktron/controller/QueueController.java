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
	 */
	@PostMapping("createQueue/{queueName}")
	public Queue<String> createQueue(@PathVariable String queueName) {
		return queueService.createQueue(queueName);
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
	 */
	@PostMapping("addToQueue/{queueName}/{queueValue}")
	public Map<String, Queue<String>> addQueue(@PathVariable String queueName, @PathVariable String queueValue) {
		queueService.add(queueName,queueValue);
		return queueService.getQueues();
	}
	
	/***
	 * clear contents of queue
	 * @param queueName
	 * @return
	 */
	@DeleteMapping("deleteAllQueueElements/{queueName}")
	public ResponseEntity<String> deleteAllQueueElements(@PathVariable String queueName){
		queueService.clearQueue(queueName);
		return new ResponseEntity("ALL Elements from "+queueName+" deleted",HttpStatus.OK);
	}
	
	/***
	 * delete queue
	 * @param queueName
	 * @return
	 */
	@DeleteMapping("deleteQueue/{queueName}")
	public ResponseEntity<String> deleteQueue(@PathVariable String queueName){
		queueService.remove(queueName);
		return new ResponseEntity("DELETED queue: "+queueName,HttpStatus.OK);
	}
	
	/***
	 * remove element from queue
	 * @param queueName
	 * @param queueValue
	 * @return
	 */
	@DeleteMapping("deleteQueueValue/{queueName}/{queueValue}")
	public ResponseEntity<String> deleteQueueValue(@PathVariable String queueName, @PathVariable String queueValue){
		queueService.remove(queueName,queueValue);
		return new ResponseEntity("DELETED queueValue: "+queueValue+" from queue: "+queueName,HttpStatus.OK);
	}
	

}
