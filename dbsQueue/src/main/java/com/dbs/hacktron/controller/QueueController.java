package com.dbs.hacktron.controller;

import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.hacktron.common.JwtUtil;
import com.dbs.hacktron.exception.JwtTokenException;
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
	 * @throws JwtTokenException 
	 */
	@PostMapping("createQueue/{queueName}/{queueLimit}")
	public ResponseEntity<Object> createQueue(@PathVariable String queueName, @PathVariable Integer queueLimit, @RequestParam(required=true) String jwtToken) throws QueueException {
		try {
			authenticate(jwtToken);
		} catch (JwtTokenException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(queueService.createQueue(queueName, queueLimit),HttpStatus.OK);
	}
	
	/***
	 * fetch contents of all queues
	 * @return
	 * @throws JwtTokenException 
	 */
	@GetMapping("fetchAllQueue")
	public ResponseEntity<Object> getQueue(@RequestParam(required=true) String jwtToken) {
		try {
			authenticate(jwtToken);
		} catch (JwtTokenException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity( queueService.getQueues(), HttpStatus.OK);
	}
	
	/***
	 * fetch contents of all queues
	 * @return
	 * @throws JwtTokenException 
	 */
	@GetMapping("fetchQueueKey")
	public ResponseEntity<Object> getQueueKey(@RequestParam(required=true) String jwtToken) {
		try {
			authenticate(jwtToken);
		} catch (JwtTokenException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(queueService.getQueues().keySet(), HttpStatus.OK);
	}
	
	/***
	 * add element to queue
	 * @param queueName
	 * @param queueValue
	 * @return
	 * @throws JwtTokenException 
	 * @throws QueueException 
	 */
	@PostMapping("addToQueue/{queueName}/{queueValue}")
	public ResponseEntity<Object> addQueue(@PathVariable String queueName, @PathVariable String queueValue, @RequestParam(required=true) String jwtToken)  {
		try {
			authenticate(jwtToken);
			queueService.add(queueName,queueValue);
		} catch (QueueException | JwtTokenException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(queueService.getQueues(), HttpStatus.OK);
	}
	
	/***
	 * clear contents of queue
	 * @param queueName
	 * @return
	 * @throws JwtTokenException 
	 * @throws QueueException 
	 */
	@DeleteMapping("deleteAllQueueElements/{queueName}")
	public ResponseEntity<String> deleteAllQueueElements(@PathVariable String queueName, @RequestParam(required=true) String jwtToken)  {
		try {
			authenticate(jwtToken);
			queueService.clearQueue(queueName);
		} catch (QueueException | JwtTokenException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity("ALL Elements from "+queueName+" deleted",HttpStatus.OK);
	}
	
	/***
	 * delete queue
	 * @param queueName
	 * @return
	 * @throws JwtTokenException 
	 * @throws QueueException 
	 */
	@DeleteMapping("deleteQueue/{queueName}")
	public ResponseEntity<String> deleteQueue(@PathVariable String queueName, @RequestParam(required=true) String jwtToken)  {
		try {
			authenticate(jwtToken);
			queueService.remove(queueName);
		} catch (QueueException | JwtTokenException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity("DELETED queue: "+queueName,HttpStatus.OK);
	}
	
	/***
	 * remove element from queue
	 * @param queueName
	 * @param queueValue
	 * @return
	 * @throws JwtTokenException 
	 * @throws QueueException 
	 */
	@DeleteMapping("deleteQueueValue/{queueName}")
	public ResponseEntity<String> deleteQueueValue(@PathVariable String queueName, @RequestParam(required=true) String jwtToken) throws JwtTokenException {
		 String response;
		try {
			authenticate(jwtToken);
			response = queueService.removeElement(queueName);
		} catch (QueueException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(response,HttpStatus.OK);
	}
	
	private Boolean authenticate(String jwtToken) throws JwtTokenException {
		return JwtUtil.tokenVerifier(jwtToken);
	}

}
