import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int[][] point;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        point = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(input.readLine());
            point[i][0] = Integer.parseInt(st.nextToken());
            point[i][1] = Integer.parseInt(st.nextToken());
        }

        int total = 0;
        for (int i = 0; i < n - 1; i++) {
            total += dist(i, i + 1);
        }

        int maxSave = 0;
        for (int i = 1; i < n - 1; i++) {
            int before = dist(i - 1, i) + dist(i, i + 1);
            int skip = dist(i - 1, i + 1);
            maxSave = Math.max(maxSave, before - skip);
        }

        System.out.println(total - maxSave);
    }

    private static int dist(int a, int b) {
        return Math.abs(point[a][0] - point[b][0]) + Math.abs(point[a][1] - point[b][1]);
    }
}
