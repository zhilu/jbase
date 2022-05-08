package concurrent.ch1.thread;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * Main class of the example. Initialize a Thread to process the uncaught
 * exceptions and starts a Task object that always throws an exception 
 *
 */
public class ExceptionThread {

	/**
	 * Main method of the example. Initialize a Thread to process the 
	 * uncaught exceptions and starts a Task object that always throws an
	 * exception 
	 * @param args
	 */
	public static void main(String[] args) {
		// Creates the Task
		Task task=new Task();
		// Creates the Thread
		Thread thread=new Thread(task);
		// Sets de uncaugh exceptio handler
		thread.setUncaughtExceptionHandler(new ExceptionHandler());
		// Starts the Thread
		thread.start();
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.printf("Thread has finished\n");

	}

}

/**
 * Runnable class than throws and Exception
 *
 */
class Task implements Runnable {


	/**
	 * Main method of the class
	 */
	@Override
	public void run() {
		// The next instruction always throws and exception
		Integer.parseInt("TTT");
	}

}


/**
 * Class that process the uncaught exceptions throwed in a Thread
 *
 */
class ExceptionHandler implements UncaughtExceptionHandler {


	/**
	 * Main method of the class. It process the uncaught excpetions throwed
	 * in a Thread
	 * @param t The Thead than throws the Exception
	 * @param e The Exception throwed
	 */
	@Override	
	public void uncaughtException(Thread t, Throwable e) {
		System.out.printf("An exception has been captured\n");
		System.out.printf("Thread: %s\n",t.getId());
		System.out.printf("Exception: %s: %s\n",e.getClass().getName(),e.getMessage());
		System.out.printf("Stack Trace: \n");
		e.printStackTrace(System.out);
		System.out.printf("Thread status: %s\n",t.getState());
	}

}

