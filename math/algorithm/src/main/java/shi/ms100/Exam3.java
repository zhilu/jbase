package shi.ms100;

/**
 * 输入一个整形数组，数组里有正数也有负数。 <br>
 * 
 * 数组中连续的一个或多个整数组成一个子数组，每个子数组都有一个和。 <br>
 * 求所有子数组的和的最大值。要求时间复杂度为O(n)。 <br>
 * <p>
 * 例如输入的数组为1, -2, 3, 10, -4, 7, 2, -5，和最大的子数组为3, 10, -4, 7, 2，<br>
 * 因此输出为该子数组的和18。
 * 
 * @author shi
 *
 */
public class Exam3 {
	public static void main(String[] args) {
		int[] input = { 1, -2, 3, 10, -4, 7, 2, -5 };

		int sum = 0;
		int b = 0;
		for (int i = 0; i < input.length; i++) {
			if (b <= 0)
				b = input[i];
			else
				b += input[i];
			if (sum < b)
				sum = b;
			System.out.println(b+","+sum);
		}

		System.out.println("最大值：" + sum);
	}
}
