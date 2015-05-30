package com.thread.latch;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor1 implements Runnable  {

	CyclicBarrier barrier;
	
	Processor1(CyclicBarrier barrier)  {
		this.barrier = barrier;
	}
	
	@Override
	public void run() {
		System.out.println("Thread "+Thread.currentThread().getName()+" awaiting...");
		try {
			barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
		System.out.println("Thread "+Thread.currentThread().getName()+" crossed the barrier...");
	}
	
}

public class AppBarrier {

	public static void main(String[] args) {
		CyclicBarrier barrier = new CyclicBarrier(3, new Runnable()  {

			@Override
			public void run() {
				System.out.println("All threads crossed the barrier.  Lets Play!!!");
			}
			
		});
		
		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		executor.submit(new Processor1(barrier));
		executor.submit(new Processor1(barrier));
		executor.submit(new Processor1(barrier));
		
		executor.shutdown();
		
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
