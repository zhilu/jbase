package concurrent.ch1.thread;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class GroupThread {

	/**
	 * Main class of the example
	 * @param args
	 */
	public static void main(String[] args) {

		// Create a ThreadGroup
		ThreadGroup threadGroup = new ThreadGroup("Searcher");
		Result result=new Result();

		// Create a SeachTask and 10 Thread objects with this Runnable
		SearchTask searchTask=new SearchTask(result);
		for (int i=0; i<5; i++) {
			Thread thread=new Thread(threadGroup, searchTask);
			thread.start();
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// Write information about the ThreadGroup to the console
		System.out.printf("Number of Threads: %d\n",threadGroup.activeCount());
		System.out.printf("Information about the Thread Group\n");
		threadGroup.list();

		// Write information about the status of the Thread objects to the console
		Thread[] threads=new Thread[threadGroup.activeCount()];
		threadGroup.enumerate(threads);
		for (int i=0; i<threadGroup.activeCount(); i++) {
			System.out.printf("Thread %s: %s\n",threads[i].getName(),threads[i].getState());
		}

		// Wait for the finalization of the Threadds
		waitFinish(threadGroup);
		
		// Interrupt all the Thread objects assigned to the ThreadGroup
		threadGroup.interrupt();
	}

	/**
	 * Method that waits for the finalization of one of the ten Thread objects
	 * assigned to the ThreadGroup
	 * @param threadGroup
	 */
	private static void waitFinish(ThreadGroup threadGroup) {
		while (threadGroup.activeCount()>9) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}


/**
 * Class that simulates a search operation
 *
 */
class SearchTask implements Runnable {

	/**
	 * Store the name of the Thread if this Thread finish and is not interrupted
	 */
	private Result result;
	
	/**
	 * Constructor of the class
	 * @param result Parameter to initialize the object that stores the results
	 */
	public SearchTask(Result result) {
		this.result=result;
	}

	@Override
	public void run() {
		String name=Thread.currentThread().getName();
		System.out.printf("Thread %s: Start\n",name);
		try {
			doTask();
			result.setName(name);
		} catch (InterruptedException e) {
			System.out.printf("Thread %s: Interrupted\n",name);
			return;
		}
		System.out.printf("Thread %s: End\n",name);
	}
	
	/**
	 * Method that simulates the search operation
	 * @throws InterruptedException Throws this exception if the Thread is interrupted
	 */
	private void doTask() throws InterruptedException {
		Random random=new Random((new Date()).getTime());
		int value=(int)(random.nextDouble()*100);
		System.out.printf("Thread %s: %d\n",Thread.currentThread().getName(),value);
		TimeUnit.SECONDS.sleep(value);
	}

}


/**
 * Class that stores the result of the search
 *
 */
class Result {
	
	/**
	 * Name of the Thread that finish
	 */
	private String name;
	
	/**
	 * Read the name of the Thread
	 * @return The name of the Thread
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Write the name of the Thread
	 * @param name The name of the Thread
	 */
	public void setName(String name) {
		this.name = name;
	}

}

