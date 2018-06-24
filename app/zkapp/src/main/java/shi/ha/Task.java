package shi.ha;

public class Task {
    
	public void work() throws InterruptedException {
		System.out.println(Thread.currentThread().getName()+"running");
		Thread.sleep(10*1000);
	}
}
