import java.util.HashMap;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int N = input.nextInt();
		StringBuilder sb = new StringBuilder();
		HashMap<Integer, Integer> card = new HashMap<>();
		for (int i = 0; i < N; i++) {
			int n = input.nextInt();
			if (card.get(n) == (null)) {
				card.put(n, 1);
			} else {
				card.put(n, card.get(n) + 1);
			}
		}
		int M = input.nextInt();
		int[] arr = new int[M];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = input.nextInt();
			if (card.get(arr[i]) == (null)) {
				sb.append(0).append(" ");
			} else {
				sb.append(card.get(arr[i])).append(" ");
			}
		}
		System.out.println(sb);
	}
}