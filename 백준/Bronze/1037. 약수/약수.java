import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int N = input.nextInt();
		int[] num = new int[N];
		int answer = 0;

		for (int i = 0; i < num.length; i++) {
			num[i] = input.nextInt();
		}
		Arrays.sort(num);
		if (N == 1) {
			answer = num[0] * num[0];
		}
		if (N != 1) {
			answer = num[0] * num[N - 1];
		}
		System.out.println(answer);
	}
}