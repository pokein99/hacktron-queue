package com.dbs.hacktron.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "QUEUE_INFORMATION")
public class QueueInformation {
	@Id
	@GeneratedValue
	@Column(name = "QUEUE_ID", unique = true, nullable = false)
	Integer queueId;
	
	@Column(name = "QUEUE_NAME", nullable = false)
	String queueName;
	
	@Column(name = "EMPLOQUEUE_CAPACITY", nullable = false)
	Integer queueCapacity;
	
	@Column(name = "QUEUE_SIZE", nullable = false)
	Integer queueSize;

	/**
	 * @return the queueId
	 */
	public Integer getQueueId() {
		return queueId;
	}

	/**
	 * @param queueId the queueId to set
	 */
	public void setQueueId(Integer queueId) {
		this.queueId = queueId;
	}

	/**
	 * @return the queueName
	 */
	public String getQueueName() {
		return queueName;
	}

	/**
	 * @param queueName the queueName to set
	 */
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	/**
	 * @return the queueCapacity
	 */
	public Integer getQueueCapacity() {
		return queueCapacity;
	}

	/**
	 * @param queueCapacity the queueCapacity to set
	 */
	public void setQueueCapacity(Integer queueCapacity) {
		this.queueCapacity = queueCapacity;
	}

	/**
	 * @return the queueSize
	 */
	public Integer getQueueSize() {
		return queueSize;
	}

	/**
	 * @param queueSize the queueSize to set
	 */
	public void setQueueSize(Integer queueSize) {
		this.queueSize = queueSize;
	}
	
	public QueueInformation(){
		
	}
}
