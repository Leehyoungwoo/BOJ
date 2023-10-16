import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int K = input.nextInt();
		Stack<Integer> stack = new Stack<>();
		int sum = 0;

		for (int i = 0; i < K; i++) {
			int x = input.nextInt();

			if (x != 0) {
				stack.add(x);
			} else {
				stack.pop();
			}
		}
		for (int size = stack.size(), i = 0; i<size; i++) {
			sum += stack.pop();
		}
		System.out.println(sum);
	}
}