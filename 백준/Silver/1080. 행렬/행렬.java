import java.util.*;
import java.io.*;

public class Main {

    private static int n, m;
    private static int[][] mapA, mapB;

    public static void main(String[] args) throws IOException {
        init();
        int answer = findAnswer();
        System.out.println(answer);
    }

    private static int findAnswer() {
        int count = 0;

        for (int i = 0; i < n - 2; i++) {
            for (int j = 0; j < m - 2; j++) {
                if (mapA[i][j] != mapB[i][j]) {
                    flip(i, j);
                    count++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mapA[i][j] != mapB[i][j]) {
                    return -1;
                }
            }
        }

        return count;
    }

    private static void flip(int x, int y) {
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                mapA[i][j] = 1 - mapA[i][j];
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        mapA = new int[n][m];
        mapB = new int[n][m];

        for (int i = 0; i < n; i++) {
            String line = input.readLine();
            for (int j = 0; j < m; j++) {
                mapA[i][j] = line.charAt(j) - '0';
            }
        }

        for (int i = 0; i < n; i++) {
            String line = input.readLine();
            for (int j = 0; j < m; j++) {
                mapB[i][j] = line.charAt(j) - '0';
            }
        }
    }
}