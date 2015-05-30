
public class SynchApp {

	private int count = 0;
	public static void main(String[] args) {
		SynchApp app = new SynchApp();
		
		app.doWork();
		
		System.out.println("count: "+app.count);
	}

	private void increment() {
		count++;
	}

	private void doWork() {
		Thread t1 = new Thread(new Runnable()  {

			@Override
			public void run() {
				
				for(int i=0; i<10_000; i++)  {
					increment();
				}
			}

		});
		
		t1.start();

		Thread t2 = new Thread(new Runnable()  {

			@Override
			public void run() {
				
				for(int i=0; i<10_000; i++)  {
					increment();
				}
			}
			
		});
		
		t2.start();

		Thread t3 = new Thread(new Runnable()  {

			@Override
			public void run() {
				
				for(int i=0; i<10_000; i++)  {
					increment();
				}
			}
			
		});
		
		t3.start();

		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
