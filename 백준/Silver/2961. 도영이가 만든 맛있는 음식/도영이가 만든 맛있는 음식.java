import java.io.*;
import java.util.*;
import java.math.*;

public class Main {

    private static int n;
    private static int[] sweet;
    private static int[] bit;

    public static void main(String[] args) throws IOException {
        init();
        long answer = findAnswer();

        System.out.println(answer);
    }

    private static long findAnswer() {
        long minDiff = Integer.MAX_VALUE;
        for (int mask = 1; mask < (1 << n); mask++) {
            long sour = 1;
            long bitt = 0;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    sour *= sweet[i];
                    bitt += bit[i];
                }
            }

            minDiff = Math.min(minDiff, Math.abs(sour - bitt));
        }

        return minDiff;
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        sweet = new int[n];
        bit = new int[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer token = new StringTokenizer(input.readLine());
            sweet[i] = Integer.parseInt(token.nextToken());
            bit[i] = Integer.parseInt(token.nextToken());
        }
    }
}