package com.thread.latch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


class Processor implements Runnable  {

	CountDownLatch latch;
	
	Processor(CountDownLatch latch)  {
		this.latch = latch;
	}
	
	@Override
	public void run() {
		System.out.println("Started: ");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Completed...");
		
		latch.countDown();
	}
	
}
public class LatchApp {

	public static void main(String[] args) {
		
		CountDownLatch latch = new CountDownLatch(3);
		
		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		for(int i=0; i<3; i++)  {
			executor.submit(new Processor(latch));
		}
		System.out.println("Submit completed!!!.. Waiting for latch countdown...");
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Latch countdown completed!!!");
	}

}
