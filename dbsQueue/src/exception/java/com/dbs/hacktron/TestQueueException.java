package com.dbs.hacktron

import hacktron.exception.QueueException;

public class TestQueueException {
	
	
	static void isfull(String message, int qid) throws QueueException
	{
		throw new QueueException("Queue is full!",qid);
	}
	public static void main(String [] args)
	{
		String s = "testmesssage";
		int qid = 5;
		try{
			isfull(s,qid);
		}
		catch(Exception e)
		{
			System.out.println("Exception occurred: "+ e.getMessage());			
		}
	}

}
