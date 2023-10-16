import java.util.Scanner;

public class Main {
	static int[][] map;
	static int bingo;

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		map = new int[5][5];
		bingo = 0;

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				map[i][j] = input.nextInt();
			}
		}
		for (int a = 1; a <= 25; a++) {
			int num = input.nextInt();

			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if (map[i][j] == num)
						map[i][j] = 0;
				}
			}
			rowCheck();
			colCheck();
			leftCheck();
			rightCheck();

			if (bingo >= 3) {
				System.out.println(a);
				break;
			}
			bingo = 0;
		}
	}

	public static void rowCheck() {
		for (int i = 0; i < 5; i++) {
			int cnt = 0;
			for (int j = 0; j < 5; j++) {
				if (map[i][j] == 0) {
					cnt++;
				}
				if (cnt == 5) {
					bingo++;
				}
			}
		}
	}

	public static void colCheck() {
		for (int i = 0; i < 5; i++) {
			int cnt = 0;
			for (int j = 0; j < 5; j++) {
				if (map[j][i] == 0) {
					cnt++;
				}
				if (cnt == 5) {
					bingo++;
				}
			}
		}
	}

	public static void leftCheck() {
		int cnt = 0;
		for (int i = 0; i < 5; i++) {
			if (map[i][i] == 0) {
				cnt++;
			}
			if (cnt == 5) {
				bingo++;
			}
		}
	}

	public static void rightCheck() {
		int cnt = 0;
		for (int i = 0; i < 5; i++) {
			if (map[i][4 - i] == 0) {
				cnt++;
			}
			if (cnt == 5) {
				bingo++;
			}
		}
	}
}