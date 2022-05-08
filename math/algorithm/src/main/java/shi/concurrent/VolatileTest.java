package shi.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * volatile变量自增运算测试
 * 
 * @author zzm
 */
public class VolatileTest {
	public static volatile int race = 0;

	public static int staticRace=0;
	
	public static Lock lock = new ReentrantLock();
	
	public static void increase() {
		race++;
		staticRace++;
	}
	public static synchronized void increaseSyn() {
		race++;
		staticRace++;
	}
	
	public static void increaseLock() {
		lock.lock();
		try {
			race++;	
			staticRace++;
		} finally {
			lock.unlock();
		}
		
	}

	private static final int THREADS_COUNT = 20;


	
	/**
	 * 非同步方法会导致并发结果不正确
	 */
	public static void testNonSyn(){
		Thread[] threads = new Thread[THREADS_COUNT];
		for (int i = 0; i < THREADS_COUNT; i++) {
			threads[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					for (int i = 0; i < 10000; i++) {
						increase();
					}
				}
			});
			threads[i].start();
		}
		// 等待所有累加线程都结束
		while (Thread.activeCount() > 1) {
			Thread.yield();
		}
		System.out.println(race);
		System.out.println(staticRace);
	}
	/**
	 * 同步方法并发结果正确
	 */
	public static void testSyn(){
		Thread[] threads = new Thread[THREADS_COUNT];
		for (int i = 0; i < THREADS_COUNT; i++) {
			threads[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					for (int i = 0; i < 10000; i++) {
						increaseSyn();
					}
				}
			});
			threads[i].start();
		}
		// 等待所有累加线程都结束
		while (Thread.activeCount() > 1) {
			Thread.yield();
		}
		System.out.println(race);
		System.out.println(staticRace);
	}
	/**
	 * 锁并发结果正确
	 */
	public static void testLock(){
		Thread[] threads = new Thread[THREADS_COUNT];
		for (int i = 0; i < THREADS_COUNT; i++) {
			threads[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					for (int i = 0; i < 10000; i++) {
						increaseLock();
					}
				}
			});
			threads[i].start();
		}
		// 等待所有累加线程都结束
		while (Thread.activeCount() > 1) {
			Thread.yield();
		}
		System.out.println(race);
		System.out.println(staticRace);
	}
	
	public static void main(String[] args) {
		testSyn();
	}
}
