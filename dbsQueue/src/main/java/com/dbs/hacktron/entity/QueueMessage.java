package com.dbs.hacktron.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "QUEUE_MESSAGE")
public class QueueMessage {
	@Id
	@GeneratedValue
	@Column(name = "QUEUE_MESSAGE_ID", unique = true, nullable = false)
	Integer queueMessageId;
	
	@ManyToOne
	@JoinColumn(name = "QUEUE_ID")
	QueueInformation queueInformation;

	@Column(name = "MESSAGE", nullable = false)
	String message;

	/**
	 * @return the queueMessageId
	 */
	public Integer getQueueMessageId() {
		return queueMessageId;
	}

	/**
	 * @param queueMessageId the queueMessageId to set
	 */
	public void setQueueMessageId(Integer queueMessageId) {
		this.queueMessageId = queueMessageId;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the queueInformation
	 */
	public QueueInformation getQueueInformation() {
		return queueInformation;
	}

	/**
	 * @param queueInformation the queueInformation to set
	 */
	public void setQueueInformation(QueueInformation queueInformation) {
		this.queueInformation = queueInformation;
	}
}
