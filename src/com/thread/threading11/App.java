package com.thread.threading11;

public class App {

	public static void main(String[] args) throws InterruptedException {
		final Processor processor = new Processor();
		Thread t1 = new Thread(() -> {
			processor.firstThread();
		});
		
		Thread t2 = new Thread(() -> {
			processor.secondThread();
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		processor.finished();
	}

}
