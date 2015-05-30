package com.thread.threading10;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
	
	private Lock lock = new ReentrantLock();
	private Condition cond = lock.newCondition();
	
	private int count = 0;
	
	private void increment()  {
		for(int i=0; i<10_000; i++)  {
			count++;
		}
	}
	public void firstThread() throws InterruptedException  {
		lock.lock();
		System.out.println("Thread 1 waiting....");
		cond.await();
		System.out.println("Thread 1 woken!!!");
		try  {
			increment();
		} finally  {
			lock.unlock();
		}
	}
	
	public void secondThread() throws InterruptedException  {
		Thread.sleep(1000);
		lock.lock();
		System.out.println("Press enter to continue...");
		new Scanner(System.in).nextLine();
		System.out.println("Enter pressed!!! giving control back to firstThread..");
		cond.signal();
		System.out.println("Thread 2 continuing....");
		try  {
			increment();
		} finally  {
			lock.unlock();
		}
	}
	
	public void finished()  {
		System.out.println("Count incremented to : "+count);
	}

}
