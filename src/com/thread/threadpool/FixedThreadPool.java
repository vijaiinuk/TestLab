package com.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


class Processor implements Runnable  {

	private int id;
	
	Processor(int id)  {
		this.id = id;
	}
	
	@Override
	public void run() {
		System.out.println("Starting ...:"+id);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(id + "  Completed");
	}
	
}

public class FixedThreadPool {

	public static void main(String[] args) {

		ExecutorService executor = Executors.newFixedThreadPool(2);
		
		for(int i=0; i<5; i++)  {
			executor.submit(new Processor(i));
		}
		
		System.out.println("All tasks submitted!!!");
		executor.shutdown();
		
		try {
			executor.awaitTermination(10, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("all tasks completed!!!"+executor.isTerminated());
	}

}
