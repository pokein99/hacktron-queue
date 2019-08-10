package com.dbs.hacktron

public class QueueException extends Exception{

	private int queueID;
	
	public QueueException(String s)
	{
		super(s);
	}
	
	public QueueException(String s,int qid)
	{
		super(s);
		this.queueID = qid;
	}
	
	
	public int getQueueID()
	{
		return queueID;
	}
	
	public String toString()
	{
		return super.toString();
	}
	public String getMessage()
	{
		return super.getMessage()+" QueueId :"+ queueID;
	}
}
