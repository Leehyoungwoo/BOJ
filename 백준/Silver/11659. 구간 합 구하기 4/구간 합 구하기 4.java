import java.util.*;

public class Main {
	
	private static long[] nums;
	private static int[] str; 
	private static int[] end;
	private static long[] sumArr;

	public static void main(String[] args) {
		init();
		totalSum();
		printAnswer();
	}
	
	private static void totalSum() {
		long sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum+=nums[i];
			sumArr[i] = sum;
		}
	}
	
	private static void printAnswer() {
		for (int i = 0; i < str.length; i++) {
			System.out.println(sumArr[end[i] - 1] - sumArr[str[i] - 1] + nums[str[i] - 1]);
		}
	}
	
	private static void init() {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		int m = input.nextInt();
		
		nums = new long[n];
		str = new int[m];
		end = new int[m];
		sumArr = new long[n];
		
		for (int i = 0; i < nums.length; i++) {
			nums[i] = input.nextLong();
		}
		
		for (int i = 0; i < m; i++) {
			str[i] = input.nextInt();
			end[i] = input.nextInt();
		}
	}
}