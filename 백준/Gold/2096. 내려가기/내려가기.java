import java.io.*;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        init();
        findMaxMinDp();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        map = new int[n][3]; 
        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
    }

    private static void findMaxMinDp() {
        int[] maxDp = new int[3];
        int[] minDp = new int[3];
        int[] tempMaxDp = new int[3];
        int[] tempMinDp = new int[3];

        for (int i = 0; i < n; i++) {
            tempMaxDp[0] = map[i][0] + Math.max(maxDp[0], maxDp[1]);
            tempMaxDp[1] = map[i][1] + Math.max(Math.max(maxDp[0], maxDp[1]), maxDp[2]);
            tempMaxDp[2] = map[i][2] + Math.max(maxDp[1], maxDp[2]);

            tempMinDp[0] = map[i][0] + Math.min(minDp[0], minDp[1]);
            tempMinDp[1] = map[i][1] + Math.min(Math.min(minDp[0], minDp[1]), minDp[2]);
            tempMinDp[2] = map[i][2] + Math.min(minDp[1], minDp[2]);

            maxDp[0] = tempMaxDp[0];
            maxDp[1] = tempMaxDp[1];
            maxDp[2] = tempMaxDp[2];

            minDp[0] = tempMinDp[0];
            minDp[1] = tempMinDp[1];
            minDp[2] = tempMinDp[2];
        }

        int maxResult = Math.max(Math.max(maxDp[0], maxDp[1]), maxDp[2]);
        int minResult = Math.min(Math.min(minDp[0], minDp[1]), minDp[2]);

        System.out.println(maxResult + " " + minResult);
    }
}