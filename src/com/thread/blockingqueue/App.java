package com.thread.blockingqueue;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {

	private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
	
	
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new Runnable()  {

			@Override
			public void run() {
				try {
					producer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		});
		
		t1.start();
		Thread t2 = new Thread(new Runnable()  {

			@Override
			public void run() {
				try {
					consumer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		});
		
		t2.start();
		
		System.out.println("Waiting to join...");
		t1.join();
		t2.join();
	}



	protected static void consumer() throws InterruptedException {
		Random random = new Random();
		
		while(true)  {
			if(random.nextInt(10) == 0)  {
				System.out.println(queue.take());
				System.out.println("Queue size : "+queue.size());
			}
		}
	}


	protected static void producer() throws InterruptedException {
		Random random = new Random();
		
		while(true)  {
			queue.put(random.nextInt(100));
		}
	}

}
