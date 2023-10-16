import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	private static int n;
	private static int[][] map;
	private static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws IOException {
		init();
		findResult(0, 0, n);
		System.out.println(answer);
	}

	private static void findResult(int row, int col, int size) {
		if(size == 1) {
			answer.append(map[row][col]);
			return; 
		}
		if(allSame(row, col, size)) {
			answer.append(map[row][col]);
			return;
		}
		answer.append("(");
		findResult(row, col, size / 2);
		findResult(row, col + size / 2, size / 2);
		findResult(row + size / 2, col, size / 2);
		findResult(row + size / 2, col + size / 2, size / 2);
		answer.append(")");
	}


	private static boolean allSame(int row, int col, int size) {
		int num = map[row][col];
		for (int i = row; i < row + size; i++) {
			for (int j = col; j < col + size; j++) {
				if(map[i][j] != num) {
					return false;
				}
			}
		}
		
		return true;
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < n; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
	}
}