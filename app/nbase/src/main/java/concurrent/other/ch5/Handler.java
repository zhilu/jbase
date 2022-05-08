package concurrent.other.ch5;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * This class manages the exceptions thrown by the worker threads.
 * 
 * Implements the interface every class of this kind has to implement
 *
 */
public class Handler implements UncaughtExceptionHandler {

	/**
	 * This method process the uncaught exceptions thrown in a 
	 * worker thread. 
	 * @param t The thread that throws the exception
	 * @param e The exception it was thrown
	 */
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.printf("Handler: Thread %s has thrown an Exception.\n",t.getName());
		System.out.printf("%s\n",e);
		System.exit(-1);
	}

}
