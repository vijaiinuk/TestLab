package com.thread.threading9;

import java.util.LinkedList;

public class Processor {
	
	private LinkedList<Integer> list = new LinkedList<>();
	private final int LIMIT = 10;
	private int i = 0;
	private Object lock = new Object();
	
	public void produce() throws InterruptedException  {
		while(true)  {
			synchronized(lock)  {
				while(list.size() == LIMIT)  {
					lock.wait();
				}
				list.add(i++);
				lock.notify();
			}
		}
	}

	public void consume() throws InterruptedException  {
		while(true)  {
			Thread.sleep(100);
			synchronized (lock) {
				while(list.size() == 0)  {
					System.out.println("size is zero. waiting....");
					lock.wait();
				}
				System.out.print("list size: "+list.size());
				int j = list.removeFirst();
				System.out.println("; taken out : "+j);
				lock.notify();
			}
			
		}
	}
}
