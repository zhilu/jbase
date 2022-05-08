package concurrent.thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * Main class of the example. Creates a Thread factory and creates ten 
 * Thread objects using that Factory 
 *
 */
public class FactoryThread {

	/**
	 * Main method of the example. Creates a Thread factory and creates 
	 * ten Thread objects using that Factory
	 * @param args
	 */
	public static void main(String[] args) {
		// Creates the factory
		MyThreadFactory factory=new MyThreadFactory("MyThreadFactory");
		// Creates a task
		MyTask task=new MyTask();
		Thread thread;
		
		// Creates and starts ten Thread objects
		System.out.printf("Starting the Threads\n");
		for (int i=0; i<10; i++){
			thread=factory.newThread(task);
			thread.start();
		}
		// Prints the statistics of the ThreadFactory to the console
		System.out.printf("Factory stats:\n");
		System.out.printf("%s\n",factory.getStats());
		
	}

}


/**
 * Class that implements the ThreadFactory interface to
 * create a basic thread factory
 *
 */
class MyThreadFactory implements ThreadFactory {

	// Attributes to save the necessary data to the factory
	private int counter;
	private String name;
	private List<String> stats;
	
	/**
	 * Constructor of the class
	 * @param name Base name of the Thread objects created by this Factory
	 */
	public MyThreadFactory(String name){
		counter=0;
		this.name=name;
		stats=new ArrayList<String>();
	}
	
	/**
	 * Method that creates a new Thread object using a Runnable object
	 * @param r: Runnable object to create the new Thread
	 */
	@Override
	public Thread newThread(Runnable r) {
		// Create the new Thread object
		Thread t=new Thread(r,name+"-Thread_"+counter);
		counter++;
		// Actualize the statistics of the factory
		stats.add(String.format("Created thread %d with name %s on %s\n",t.getId(),t.getName(),new Date()));
		return t;
	}
	
	/**
	 * Method that returns the statistics of the ThreadFactory 
	 * @return The statistics of the ThreadFactory
	 */
	public String getStats(){
		StringBuffer buffer=new StringBuffer();
		Iterator<String> it=stats.iterator();
		
		while (it.hasNext()) {
			buffer.append(it.next());
		}
		
		return buffer.toString();
	}

}

class MyTask implements Runnable {

	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

