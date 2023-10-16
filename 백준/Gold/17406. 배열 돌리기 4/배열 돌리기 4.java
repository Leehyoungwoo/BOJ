import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int M;
    private static int K;
    private static String[] rcs;
    private static int [][] map;
    private static int[][] rotatedMap;
    private static int[] permutation;
    private static boolean[] pVisited;

    private static int minSum = Integer.MAX_VALUE;


    public static void main(String[] args) throws IOException {
        init();
        permutation = new int[K];
        generatePermutations(0, new boolean[K]);

        System.out.println(minSum);
    }

    private static void generatePermutations(int index, boolean[] pVisited) {
        if (index == K) {
            int[][] temp = copyMap();

            for (int i = 0; i < K; i++) {
                rotateAll(rcs[permutation[i]], temp);
            }

            int rowMin = findMinRow(temp);
            minSum = Math.min(minSum, rowMin);
            return;
        }

        for (int i = 0; i < K; i++) {
            if (!pVisited[i]) {
                pVisited[i] = true;
                permutation[index] = i;
                generatePermutations(index + 1, pVisited);
                pVisited[i] = false;
            }
        }
    }
    
    private static int[][] copyMap() {
    	int[][] temp = new int[N+1][M+1];
    	for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				temp[i][j] = map[i][j];
			}
		}
    	return temp;
    }


    private static int findMinRow(int[][] map) {
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < map.length; i++) {
            int sum = 0;
            for (int j = 1; j < map[i].length; j++) {
                sum+=map[i][j];
            }
            min = Math.min(min, sum);
        }
        return min;
    }

    private static void rotateAll(String str, int[][] map) {
        String[] range = str.split(" ");
        int r = Integer.parseInt(range[0]);
        int c = Integer.parseInt(range[1]);
        int s = Integer.parseInt(range[2]);

        int layers = s;
        
        for (int i = 0; i < layers; i++) {
            int element = 2 * (r - 1 - i) *(c - 1 - i) * 4;
            rotateMap(i, r, c, s, element, map);
        }
    }

    private static void rotateMap(int n, int r, int c, int s, int element, int[][] map) {
        rotatedMap = new int[N+1][M+1];
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        int curX = c - s + n;
        int curY = r - s + n;
        int cnt = 0;

        while (cnt != element) {
            cnt++;
            if (curY == r - s + n&& (c - s + n<= (curX + 1) && (curX + 1) <= c + s - n)) {
                int newX = curX+ dx[0];
                int newY = curY + dy[0];
                rotatedMap[newY][newX] = map[curY][curX];
                curX+=dx[0];
                curY+=dy[0];
                continue;
            }

            if (curX == c + s - n&& (r- s + n<= (curY + 1) && (curY + 1) <= r + s - n)) {
                int newX = curX + dx[1];
                int newY = curY + dy[1];
                rotatedMap[newY][newX] = map[curY][curX];
                curX+=dx[1];
                curY+=dy[1];
                continue;
            }

            if (curY == r+s - n&& (c-s + n<= (curX - 1) && (curX - 1) <= c + s - n)) {
                int newX = curX + dx[2];
                int newY = curY + dy[2];
                rotatedMap[newY][newX] = map[curY][curX];
                curX+=dx[2];
                curY+=dy[2];
                continue;
            }

            if (curX == c - s + n && (r-s + n<= (curY - 1) && (curY - 1) <= r + s - n)) {
                int newX = curX + dx[3];
                int newY = curY + dy[3];
                rotatedMap[newY][newX] = map[curY][curX];
                curX+=dx[3];
                curY+=dy[3];
            }
        }
        for (int i = r-s + n; i <= r + s - n; i++) {
            for (int j = c -s + n; j <= c+s - n; j++) {
                if (rotatedMap[i][j] == 0 || map[i][j] == rotatedMap[i][j]) {
                    continue;
                }

                map[i][j] = rotatedMap[i][j];
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 1];
        rcs = new String[K];

        for (int i = 1; i < map.length; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            for (int j = 1; j < map[i].length; j++) {
                map[i][j] = Integer.parseInt(st1.nextToken());
            }
        }

        for (int i = 0; i < rcs.length; i++) {
            rcs[i] = br.readLine();
        }
    }
}