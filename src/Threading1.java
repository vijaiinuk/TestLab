import java.lang.reflect.Field;


public class Threading1 {
	
	public static void main(String[] args) {
		System.out.println("Hello world!!!");
		
		Worker worker = new Worker();
		
		worker.start();

		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			Field field = worker.getClass().getDeclaredField("running");
			field.setAccessible(true);
			field.set(worker, false);
		} catch (IllegalArgumentException | IllegalAccessException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			worker.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
