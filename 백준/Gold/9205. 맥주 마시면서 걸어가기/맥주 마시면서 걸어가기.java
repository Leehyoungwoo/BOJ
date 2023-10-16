import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Node {
	int row;
	int col;

	public Node(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int caculateDis(Node target) {
		return Math.abs(this.row - target.row) + Math.abs(this.col - target.col);
	}
}

public class Main {

	private static final int 갈수있는거리 = 50 * 20;
	private static int t;
	private static int n;
	private static List<Node> 편의점들;
	private static Node 상근이집;
	private static Node 축제;

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder builder = new StringBuilder();
		t = Integer.parseInt(input.readLine());

		for (int tc = 1; tc <= t; tc++) {
			init(input);
			String answer = findAnswer();
			builder.append(answer).append("\n");
		}

		System.out.println(builder);
	}

	private static void init(BufferedReader input) throws IOException {
		n = Integer.parseInt(input.readLine());
		String[] 상근집 = input.readLine().split(" ");
		상근이집 = new Node(Integer.parseInt(상근집[0]), Integer.parseInt(상근집[1]));
		편의점들 = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			String[] 편의점 = input.readLine().split(" ");
			편의점들.add(new Node(Integer.parseInt(편의점[0]), Integer.parseInt(편의점[1])));
		}

		String[] 축제임 = input.readLine().split(" ");
		축제 = new Node(Integer.parseInt(축제임[0]), Integer.parseInt(축제임[1]));
	}

	private static String findAnswer() {
		boolean canFestival = false;
		Queue<Node> que = new LinkedList<>();
		que.offer(상근이집);
		boolean[] visited = new boolean[n];

		while (!que.isEmpty()) {
			Node cur = que.poll();

			if (cur.caculateDis(축제) <= 갈수있는거리) {
				canFestival = true;
				break;
			}
			
			for (int i = 0; i < n; i++) {
				Node node = 편의점들.get(i);
				if (cur.caculateDis(node) <= 갈수있는거리 && !visited[i]) {
					que.offer(node);
					visited[i] = true;
				}
			}
			
		}

		return canFestival ? "happy" : "sad";
	}

}