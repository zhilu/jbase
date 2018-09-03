package concurrent.ch1.thread;

/**
 * 最简单的多线程方式
 */
public class SimpleThread {

	public static void main(String[] args) {

		for (int i = 1; i <= 10; i++) {
			Calculator calculator = new Calculator(i);
			Thread thread = new Thread(calculator);
			thread.start();
		}
	}
}

class Calculator implements Runnable {

	private int number;
	
	/**
	 *  Constructor of the class
	 * @param number : The number
	 */
	public Calculator(int number) {
		this.number=number;
	}
	
	/**
	 *  Method that do the calculations
	 */
	@Override
	public void run() {
		for (int i=1; i<=10; i++){
			System.out.printf("%s: %d * %d = %d\n",Thread.currentThread().getName(),number,i,i*number);
		}
	}

}
