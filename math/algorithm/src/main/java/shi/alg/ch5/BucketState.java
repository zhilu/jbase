package shi.alg.ch5;

import java.util.Arrays;

public class BucketState {

	public static final int BUCKETS_COUNT = 3;

	public static final int BUCKET_INIT_STATE[] = { 8, 0, 0 };
	public static final int BUCKET_FINAL_STATE[] = { 4, 4, 0 };
	public static final int BUCKET_CAPICITY[] = { 8, 5, 3 };
	
	public static final BucketState INIT_STATE=new BucketState(BUCKET_INIT_STATE);
	public static final BucketState FINAL_STATE=new BucketState(BUCKET_FINAL_STATE);
	
	public class ACTION {
		int from;
		int to;
		int water;
	}

	private int bucket_s[] = new int[BUCKETS_COUNT];
	private ACTION curAction = new ACTION();

	public BucketState() {
		SetBuckets(BUCKET_INIT_STATE);
		SetAction(8, -1, 0);
	}

	public BucketState(int[] buckets) {
		SetBuckets(buckets);
		SetAction(8, -1, 0);
	}

	public BucketState(BucketState state) {
		SetBuckets(state.bucket_s);
		SetAction(state.curAction.water, state.curAction.from, state.curAction.to);
	}

	boolean IsSameState(BucketState state) {
		for (int i = 0; i < BUCKETS_COUNT; ++i) {
			if (bucket_s[i] != state.bucket_s[i])
				return false;
		}
		return true;
	}

	void SetAction(int w, int f, int t) {
		curAction.water = w;
		curAction.from = f;
		curAction.to = t;
	}

	void SetBuckets(int[] buckets) {
		for (int i = 0; i < BUCKETS_COUNT; ++i) {
			bucket_s[i] = buckets[i];
		}
	}

	boolean CanTakeDumpAction(int from, int to) {
		assert ((from >= 0) && (from < BUCKETS_COUNT));
		assert ((to >= 0) && (to < BUCKETS_COUNT));
		/* 不是同一个桶，且from桶中有水，且to桶中不满 */

		if ((from != to) && !IsBucketEmpty(from) && !IsBucketFull(to)) {
			return true;
		}

		return false;
	}

	boolean IsBucketEmpty(int bucket) {
		assert ((bucket >= 0) && (bucket < BUCKETS_COUNT));
		return (bucket_s[bucket] == 0);
	}

	boolean IsBucketFull(int bucket) {
		assert ((bucket >= 0) && (bucket < BUCKETS_COUNT));
		return (bucket_s[bucket] >= BUCKET_CAPICITY[bucket]);
	}

	void PrintStates() {
		System.out.print(String.format("Dump %s  water from %s to %s ,buckets water states is : ", curAction.water,
				curAction.from, curAction.to));
		System.out.println(Arrays.toString(bucket_s));
	}

	boolean IsFinalState() {
		return IsSameState(FINAL_STATE);
	}

	boolean DumpWater(int from, int to, BucketState next) {
		next.SetBuckets(bucket_s);
		int dump_water = BUCKET_CAPICITY[to] - next.bucket_s[to];
		if (next.bucket_s[from] >= dump_water) {
			next.bucket_s[to] += dump_water;
			next.bucket_s[from] -= dump_water;
		} else {
			next.bucket_s[to] += next.bucket_s[from];
			dump_water = next.bucket_s[from];
			next.bucket_s[from] = 0;
		}
		if (dump_water > 0) /* 是一个有效的倒水动作? */
		{
			next.SetAction(dump_water, from, to);
			return true;
		}
		return false;
	}

}
