import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int N = input.nextInt();
		int K = input.nextInt();
		int[] answer = new int[N];
		int cnt = 0;
		Queue<Integer> man = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		sb.append('<');

		for (int i = 0; i < N; i++) {
			man.add(i + 1);
		}
		while (man.size() > 1) {
			for (int i = 0; i < K - 1; i++) {
				int val = man.poll();
				man.add(val);
			}
			answer[cnt] = man.poll();
			sb.append(answer[cnt]).append(", ");
			cnt++;
		}
		answer[N - 1] = man.poll();

		sb.append(answer[N-1]).append('>');
		System.out.println(sb);
	}
}