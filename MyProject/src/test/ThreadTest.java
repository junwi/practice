package test;

public class ThreadTest implements Runnable {

	@Override
	public void run() {
		while(true)
		synchronized (this) {
			System.out.println("1");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("2");
		}
	}

	public static void main(String[] args) {
		Thread[] threads = new Thread[10];
		ThreadTest t = new ThreadTest();
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(t);
			threads[i].start();
		}
	}
}
