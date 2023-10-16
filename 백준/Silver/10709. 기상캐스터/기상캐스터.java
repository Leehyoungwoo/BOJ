import java.util.*;

public class Main {
	
	private static int H;
	private static int W;
	private static char[][] map;
	private static int[][] futureCloud;
	
	public static void main(String[] args) {
		init();
		forcastWeather();
		printForcast();
	}
	
	private static void printForcast() {
		for (int i = 0; i < futureCloud.length; i++) {
			for (int j = 0; j < futureCloud[i].length; j++) {
				System.out.print(futureCloud[i][j] + " ");
			}
			System.out.println();
		}
		
	}

	private static void forcastWeather() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (isCloud(i, j)) {
					futureCloud[i][j] = 0;
					continue;
				}
				futureCloud[i][j] = cloudMoving(i, j);
			}
		}
	}


	private static int cloudMoving(int i, int j) {
		int count = 0;
		if (!isFutureCloudExist(i ,j)) {
			return -1;
		} 
		count = findCloudComeTime(i, j);
		
		return count;
	}

	private static boolean isFutureCloudExist(int i, int j) {
		for (int k = 0; k < j ; k++) {
			if(map[i][k] == 'c') {
				return true;
			}
		} 
		return false;
	}
	
	private static int findCloudComeTime(int i, int j) {
		int dist = 0;
		for (int k = j - 1; k >= 0 ; k--) {
			if(map[i][k] == 'c') {
				dist = j - k;
				break;
			}
		} 

		return dist;
	}

	private static boolean isCloud(int i, int j) {
		return map[i][j] == 'c';
	}

	private static void init() {
		Scanner input = new Scanner(System.in);
		H = input.nextInt();
		W = input.nextInt();
		map = new char[H][W];
		futureCloud = new int[H][W];
		
		for (int i = 0; i < map.length; i++) {
			String str = input.next();
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = str.charAt(j);
			}
		}
	}
}	