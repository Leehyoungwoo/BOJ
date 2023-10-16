import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int N = input.nextInt();
		int M = input.nextInt();
		int min = 0;
		int pollCnt = 0;
		Queue<Integer> queue = new LinkedList<>();
		Queue<Integer> out = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			queue.add(i + 1);
		}
		for (int i = 0; i < M; i++) {
			out.add(input.nextInt());
		}
		while (!out.isEmpty()) {
			int x = out.poll();
			int cnt = 0;
			while (x != queue.peek()) {
				int qFront = queue.poll();
				queue.add(qFront);
				cnt++;
			}
			min += Math.min(cnt, queue.size() - cnt);
			queue.poll();
		}
		System.out.println(min);
	}
}
