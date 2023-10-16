import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		int paper = input.nextInt();
		int[][] map = new int[101][101];
		int cnt = 0;

		for (int i = 0; i < paper; i++) {
			int x1 = input.nextInt();
			int y1 = input.nextInt();

			for (int y = y1; y < y1 + 10; y++) {
				for (int x = x1; x < x1 + 10; x++) {
					map[y][x] = 1;
				}
			}
		}
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == 1) {
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}
}