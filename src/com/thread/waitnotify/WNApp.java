package com.thread.waitnotify;

import java.util.Scanner;

public class WNApp {
	
	void produce() throws InterruptedException  {
		System.out.println("Start producing... ");
		synchronized(this)  {
			System.out.println("Producer waiting.. ");
			wait();
			System.out.println("Producer resumed...");
		}
	}

	void consume() throws InterruptedException  {
		Thread.sleep(3000);
		
		Scanner scanner = new Scanner(System.in);
		synchronized (this) {
			System.out.println("Hit return to continue ..");
			scanner.nextLine();
			scanner.close();
			System.out.println("enter pressed..");
			notify();
			System.out.println("Relinquishing control..");
		}
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		
		WNApp app = new WNApp();

		Thread t = new Thread(new Runnable()  {

			@Override
			public void run() {
				try {
					app.produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		t.start();
		
		Thread t2 = new Thread(new Runnable()  {

			@Override
			public void run() {
				try {
					app.consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		t2.start();

		t.join();
		t2.join();
	}

}
