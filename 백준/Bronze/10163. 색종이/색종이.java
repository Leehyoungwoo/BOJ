import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		int N = input.nextInt();
		int[][] map = new int[1001][1001];
		int[] answer = new int[N];

		for (int i = 0; i < N; i++) {
			int a = input.nextInt();
			int b = input.nextInt();
			int c = input.nextInt();
			int d = input.nextInt();

			for (int x = a; x < a + c; x++) {
				for (int y = b; y < b + d; y++) {
					map[x][y] = i + 1;
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < map.length; j++) {
				for (int k = 0; k < map[i].length; k++) {
					if (map[j][k] == i + 1) {
						answer[i]++;
					}
				}
			}
		}

		for (int i = 0; i < answer.length; i++) {
			System.out.println(answer[i]);
		}
	}
}