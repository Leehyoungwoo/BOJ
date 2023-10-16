import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Position {
	int row;
	int col;

	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}

}

public class Main {

	private static List<Position> list = new ArrayList<>();
	private static int[][] direction = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	private static int[][] map;
	private static int n;
	private static int m;
	private static int[] walls = new int[3];
	private static int maxArea;

	public static void main(String[] args) throws IOException {
		// 초기화
		init();
		// 세개 벽 세우기 벽은 빈칸에만 가능
		findWallCom(0, 0);
		System.out.println(maxArea);
	}

	private static void init() throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer = new StringTokenizer(input.readLine());

		n = Integer.parseInt(tokenizer.nextToken());
		m = Integer.parseInt(tokenizer.nextToken());
		maxArea = 0;

		map = new int[n][m];
		for (int i = 0; i < map.length; i++) {
			tokenizer = new StringTokenizer(input.readLine());
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = Integer.parseInt(tokenizer.nextToken());
				list.add(new Position(i, j));
			}
		}
	}

	private static void findWallCom(int idx, int start) {
		if (idx == 3) {
			int[][] temp = copyMap();
			if(!canMakeWall()) {
				return;
			}
			// 벽만들기
			makeWall(temp);
			int safe = findArea(temp);
			maxArea = Math.max(maxArea, safe);
			return;
		}

		if (start == list.size()) {
			return;
		}
		walls[idx] = start;
		findWallCom(idx + 1, start + 1);
		walls[idx] = -1;
		findWallCom(idx, start + 1);
	}

	private static int findArea(int[][] temp) {
		int safe = 0;
		Queue<Position> virus = new LinkedList<>();
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[i].length; j++) {
				if (temp[i][j] == 2) {
					virus.add(new Position(i, j));
				}
			}
		}

		while (!virus.isEmpty()) {
			Position cur = virus.poll();
			int curR = cur.row;
			int curC = cur.col;

			for (int[] dir : direction) {
				int nextR = curR + dir[0];
				int nextC = curC + dir[1];
				if (isPossibleToMove(nextR, nextC) && temp[nextR][nextC] == 0) {
					temp[nextR][nextC] = 2;
					virus.add(new Position(nextR, nextC));
				}
			}
		}

		safe = countSafeArea(temp);
		return safe;
	}

	private static int countSafeArea(int[][] temp) {
		int cnt = 0;

		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[i].length; j++) {
				if (temp[i][j] == 0) {
					cnt++;
				}
			}
		}

		return cnt;
	}

	private static boolean isPossibleToMove(int nextR, int nextC) {
		return (0 <= nextR && nextR < n) && (0 <= nextC && nextC < m);
	}

	private static int[][] copyMap() {
		int[][] temp = new int[n][m];

		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[i].length; j++) {
				temp[i][j] = map[i][j];
			}
		}

		return temp;
	}

	private static void makeWall(int[][] temp) {
		for (int n : walls) {
			int r = list.get(n).row;
			int c = list.get(n).col;
//			if (temp[r][c] != 0) {
//				continue;
//			}
			temp[r][c] = 1;
		}
	}

	private static boolean canMakeWall() {
		for (int i = 0; i < walls.length; i++) {
			int r = list.get(walls[i]).row;
			int c = list.get(walls[i]).col;
			if (map[r][c] != 0) {
				return false;
			}
		}

		return true;
	}
}