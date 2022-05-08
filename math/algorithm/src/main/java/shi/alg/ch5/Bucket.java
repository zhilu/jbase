package shi.alg.ch5;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class Bucket {

	public static void main(String[] args) {
		Deque<BucketState> states = new ArrayDeque<BucketState>();
		
		BucketState init = new BucketState(BucketState.BUCKET_INIT_STATE);
		states.addLast(init);
		
		SearchState(states);
		
		assert (states.size() == 1); /* 穷举结束后states应该还是只有一个初始状态 */
	}

	public static void SearchState(Deque<BucketState> states) {
		BucketState current = states.peekLast(); /* 每次都从当前状态开始 */
		if (current.IsFinalState()) {
			PrintResult(states);
			return;
		}

		/* 使用两重循环排列组合6种倒水状态 */
		for (int j = 0; j < BucketState.BUCKETS_COUNT; ++j) {
			for (int i = 0; i < BucketState.BUCKETS_COUNT; ++i) {
				SearchStateOnAction(states, current, i, j);
			}
		}
	}

	static void PrintResult(Deque<BucketState> states) {
		System.out.println("Find Result : ");
		for (BucketState bs : states) {
			bs.PrintStates();
		}
	}

	static boolean IsSameBucketState(BucketState state1, BucketState state2) {
		return state1.IsSameState(state2);
	}

	static boolean IsProcessedState(Deque<BucketState> states, BucketState newState) {
		Iterator<BucketState> iter = states.descendingIterator();
		while (iter.hasNext()) {
			BucketState bs = iter.next();
			if (IsSameBucketState(bs, newState)) {
				return true;
			}
		}
		return false;
	}

	public static boolean IsCurrentActionValid(BucketState current, int from, int to) {
		/* 不是同一个桶，且from桶中有水，且to桶中不满 */
		if ((from != to) && !current.IsBucketEmpty(from) && !current.IsBucketFull(to)) {
			return true;
		}

		return false;
	}

	public static void SearchStateOnAction(Deque<BucketState> states, BucketState current, int from, int to) {
		if (IsCurrentActionValid(current, from, to)) {
			BucketState next = new BucketState();
			/* 从from到to倒水，如果成功，返回倒水后的状态 */
			boolean bDump = current.DumpWater(from, to, next);
			if (bDump && !IsProcessedState(states, next)) {
				states.addLast(next);
				SearchState(states);
				states.removeLast();
			}
		}
	}

}
