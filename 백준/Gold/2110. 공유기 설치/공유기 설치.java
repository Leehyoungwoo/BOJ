import java.util.*;
import java.io.*;

public class Main {

    private static int n;
    private static int c;
    private static long[] house;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        c = Integer.parseInt(tokenizer.nextToken());
        house = new long[n];
        for (int i = 0; i < n; i++) {
            house[i] = Long.parseLong(input.readLine());
        }
        Arrays.sort(house);
    }

    private static void findAnswer() {
        long left = 1;
        long right = house[n - 1] - house[0];
        long answer = right;
        while (left <= right) {
            long mid = (left + right) / 2;
            if (canInstall(mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(answer);
    }

    private static boolean canInstall(long mid) {
        int count = 1;
        long lastInstalled = house[0];
        for (long home : house) {
            if (home - lastInstalled >= mid) {
                count++;
                lastInstalled = home;
            }
        }
        return count >= c;
    }
}