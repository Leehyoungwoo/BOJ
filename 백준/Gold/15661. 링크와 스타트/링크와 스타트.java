import java.io.*;
import java.util.*;
import java.math.*;

public class Main {

    private static int n;
    private static int[][] power;
    private static int minDiff;

    public static void main(String[] args) throws IOException {
        init();
        int answer = findAnswer();

        System.out.println(answer);
    }

    private static int findAnswer() {
        minDiff = Integer.MAX_VALUE;
        for (int mask = 1; mask < (1 << (n - 1)); mask++) {
            int startPower = 0;
            int linkPower = 0;

            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    boolean inStartI = (mask & (1 << i)) != 0;
                    boolean inStartJ = (mask & (1 << j)) != 0;
                    if (inStartI && inStartJ) {
                        startPower+= power[i][j] + power[j][i];
                    } else if (!inStartI && !inStartJ) {
                        linkPower+= power[i][j] + power[j][i];
                    }
                }
            }
            int diff = Math.abs(startPower - linkPower);
            minDiff = Math.min(minDiff, diff);
        }
        return minDiff;
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        power = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer token = new StringTokenizer(input.readLine());
            for (int j = 0; j < n; j++) {
                power[i][j] = Integer.parseInt(token.nextToken());
            }
        }
    }
}