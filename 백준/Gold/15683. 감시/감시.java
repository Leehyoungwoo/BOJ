import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Camera {
	int row;
	int col;
	int type;

	public Camera(int row, int col, int type) {
		this.row = row;
		this.col = col;
		this.type = type;
	}
}

public class Main {

	private static final int WALL = 6;
	private static int N;
	private static int M;
	private static int[][] map;
	private static List<Camera> cctvs = new ArrayList<>();
	private static int[] per;
	private static int minBlind;

	public static void main(String[] args) throws IOException {
		init();
		dfs(0);
		System.out.println(minBlind);
	}

	private static void init() throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		minBlind = N * M;

		map = new int[N][M];
		for (int i = 0; i < map.length; i++) {
			tokenizer = new StringTokenizer(input.readLine());
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = Integer.parseInt(tokenizer.nextToken());
				if (map[i][j] != 0 && map[i][j] != WALL) {
					cctvs.add(new Camera(i, j, map[i][j]));
				}
				if (map[i][j] != 0) {
					minBlind--;
				}
			}
		}
		per = new int[cctvs.size()];
	}

	private static void dfs(int idx) {
		if (idx == cctvs.size()) {
			int[][] temp = copyMap();
			int blindSpot = findSpot(temp);
			minBlind = Math.min(minBlind, blindSpot);
			return;
		}

		for (int i = 0; i < 4; i++) {
			per[idx] = i;
			dfs(idx + 1);
		}
	}

	private static int[][] copyMap() {
		int[][] temp = new int[N][M];

		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[i].length; j++) {
				temp[i][j] = map[i][j];
			}
		}

		return temp;
	}

	private static int findSpot(int[][] temp) {
		for (int i = 0; i < cctvs.size(); i++) {
			int dir = per[i];
			Camera cur = cctvs.get(i);
			watching(dir, cur, temp);
		}

		int unWatch = 0;
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[i].length; j++) {
				if (temp[i][j] == 0) {
					unWatch++;
				}
			}
		}

		return unWatch;
	}

	private static void watching(int direction, Camera cur, int[][] temp) {
		int[][] dir = decideDir(direction, cur);
		int curR = cur.row;
		int curC = cur.col;

		for (int[] d : dir) {
			watch(curR, curC, d, temp);
		}
	}

	private static void watch(int curR, int curC, int[] d, int[][] temp) {
		int nextR = curR + d[0];
		int nextC = curC + d[1];
		if (isInRange(nextR, nextC) && map[nextR][nextC] != WALL) {
			temp[nextR][nextC] = -1;
			watch(nextR, nextC, d, temp);
		}
	}

	private static int[][] decideDir(int dir, Camera cur) {
		final int[][] direction = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
		int type = cur.type;

		if (type == 1) {
			return new int[][] { direction[dir] };
		}

		if (type == 2) {
			if (dir == 0 || dir == 1) {
				return new int[][] { { 1, 0 }, { -1, 0 } };
			}
			if (dir == 2 || dir == 3) {
				return new int[][] { { 0, 1 }, { 0, -1 } };
			}
		}

		if (type == 3) {
			if (dir == 0) {
				return new int[][] { { 1, 0 }, { 0, -1 } };
			}
			if (dir == 1) {
				return new int[][] { { -1, 0 }, { 0, 1 } };
			}
			if (dir == 2) {
				return new int[][] { { 0, 1 }, { 1, 0 } };
			}
			if (dir == 3) {
				return new int[][] { { 0, -1 }, { -1, 0 } };
			}
		}

		if (type == 4) {
			if (dir == 0) {
				return new int[][] { { 1, 0 }, { 0, -1 }, { 0, 1 } };
			}
			if (dir == 1) {
				return new int[][] { { 1, 0 }, { 0, 1 }, { -1, 0 } };
			}
			if (dir == 2) {
				return new int[][] { { -1, 0 }, { 0, -1 }, { 0, 1 } };
			}
			if (dir == 3) {
				return new int[][] { { -1, 0 }, { 0, -1 }, { 1, 0 } };
			}

		}

		return direction;
	}

	private static boolean isInRange(int row, int col) {
		return 0 <= row && row < N && 0 <= col && col < M;
	}

}