import java.util.*;

public class Main {
	
	private static int[][] map = new int[101][101];

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		
		for (int t = 0; t < n; t++) {
			int x = input.nextInt();
			int y = input.nextInt();
			for (int i = x; i < x + 10; i++) {
				for (int j = y; j < y + 10; j++) {
					map[i][j]++;
				}
			}
		}
		System.out.print(answer());
	}

	private static int answer() {
		int round = 0;
		
		for (int i = 1; i < map.length-1; i++) {
			for (int j = 1; j < map[i].length-1; j++) {
				if(map[i][j] ==0) {
					continue;
				}
				
				if(map[i - 1][j] == 0) {
					round++;
				}
				if(map[i+1][j] == 0) {
					round++;
				}
				if(map[i][j-1] == 0) {
					round++;
				}
				if(map[i][j +1] == 0) {
					round++;
				}
			}
		}
		
		return round;
	}
}
