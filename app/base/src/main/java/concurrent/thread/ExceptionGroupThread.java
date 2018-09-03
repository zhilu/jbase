package concurrent.thread;

import java.util.Random;

/**
 * Main class of the example
 *
 */
public class ExceptionGroupThread {

	/**
	 * Main method of the example. Creates a group of threads of
	 * MyThreadGroup class and two threads inside this group
	 * @param args
	 */
	public static void main(String[] args) {

		// Create a MyThreadGroup object
		MyThreadGroup threadGroup=new MyThreadGroup("MyThreadGroup");
		// Create a Taks object
		Task2 task=new Task2();
		// Create and start two Thread objects for this Task
		for (int i=0; i<2; i++){
			Thread t=new Thread(threadGroup,task);
			t.start();
		}
	}

}


/**
 * Class that implements the concurrent task
 *
 */
class Task2 implements Runnable {

	@Override
	public void run() {
		int result;
		// Create a random number generator
		Random random=new Random(Thread.currentThread().getId());
		while (true) {
			// Generate a random number a calculate 1000 divide by that random number
			result=1000/((int)(random.nextDouble()*1000));
			System.out.printf("%s : %f\n",Thread.currentThread().getId(),result);
			// Check if the Thread has been interrupted
			if (Thread.currentThread().isInterrupted()) {
				System.out.printf("%d : Interrupted\n",Thread.currentThread().getId());
				return;
			}
		}
	}
}


/**
 * Class that extends the ThreadGroup class to implement
 * a uncaught exceptions method 
 *
 */
class MyThreadGroup extends ThreadGroup {

	/**
	 * Constructor of the class. Calls the parent class constructor
	 * @param name
	 */
	public MyThreadGroup(String name) {
		super(name);
	}


	/**
	 * Method for process the uncaught exceptions
	 */
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		// Prints the name of the Thread
		System.out.printf("The thread %s has thrown an Exception\n",t.getId());
		// Print the stack trace of the exception
		e.printStackTrace(System.out);
		// Interrupt the rest of the threads of the thread group
		System.out.printf("Terminating the rest of the Threads\n");
		interrupt();
	}
}

