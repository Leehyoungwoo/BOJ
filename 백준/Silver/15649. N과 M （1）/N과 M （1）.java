import java.util.*;

public class Main {
	
	private static int n;
	private static int m;
	private static int[] seq;
	private static boolean[] visited;
	
	public static void main(String[] args) {
		int cnt = 1;
		init();
		findAllSequence(cnt);
	}
	
	private static void findAllSequence(int cnt) {
		if (cnt == m + 1) {
			printEachSeq();
			return;
		}
		
		for (int i = 1; i <= n; i++) {
			if (!visited[i]) {
				seq[cnt] = i;
				visited[i] = true;
				findAllSequence(cnt + 1);
				visited[i] = false;	
			}
		}
	}
	
	private static void printEachSeq() {
		for (int i = 1; i < m + 1; i++) {
			if (seq[i] != 0) {
				System.out.print(seq[i] + " ");
			}
		}
		System.out.println();
	}

	private static void init() {
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		m = input.nextInt();
		seq = new int[9];
		visited = new boolean[9];
	}
}