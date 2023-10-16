import java.util.*;

public class Main {
	
	private static int[] nums;
	private static int n;
	private static int m;

	public static void main(String[] args) {
		init();
		makeSeq(0, 1);
	}

	private static void makeSeq(int cnt, int start) {
		if(cnt == m) {
			printArr();
			return;
		}
		for (int i = start; i <= n; i++) {
			nums[cnt] = i;
			makeSeq(cnt + 1, i + 1);
		}
	}

	private static void printArr() {
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + " ");
		}
		System.out.println();
	}

	private static void init() {
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		m = input.nextInt();
		nums = new int[m];
	}
}