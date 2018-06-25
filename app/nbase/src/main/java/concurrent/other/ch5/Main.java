package concurrent.other.ch5;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;


/**
 * Main class of the example
 */
public class Main {

	/**
	 * Main method of the example
	 * @param args
	 */
	public static void main(String[] args) {

		// Creates a task
		OneSecondLongTask task=new OneSecondLongTask();
		
		// Creates a new Handler
		Handler handler = new Handler();
		// Creates a Factory
		AlwaysThrowsExceptionWorkerThreadFactory factory=new AlwaysThrowsExceptionWorkerThreadFactory();
		// Creates a new ForkJoinPool
		ForkJoinPool pool=new ForkJoinPool(2,factory,handler,false);
		
		// Execute the task in the pool
		pool.execute(task);
	
		// Shutdown the pool
		pool.shutdown();
		
		// Wait for the finalization of the tasks
		try {
			pool.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.printf("Main: The program has finished.\n");
		
	}

}
