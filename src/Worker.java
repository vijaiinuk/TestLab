	public class Worker extends Thread  {
		private static boolean running = true;
		public void run()  {
			while(running)  {
				System.out.printf("Hello!!!\n");
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
