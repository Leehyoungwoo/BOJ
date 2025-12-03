import java.io.*;
import java.util.*;
import java.math.*;

public class Main {

    private static int x, y;

    public static void main(String[] args) throws IOException {
        init();
        int answer = findAnswer();

        System.out.println(answer);
    }

    private static int findAnswer() {
        // 최소 몇판 더 해야 승률이 변하지?
        // 앞으로의 모든 게임에서 지지 않음
        int left = 1;
        int right = 1_000_000_000;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (winRateChange(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        if (!winRateChange(left)) {
            return -1;
        }
        return left;
    }

    private static boolean winRateChange(int mid) {
        long Z = y * 100L / x;
        long newZ = (y + mid) * 100L / (x + mid);
        return Z < newZ;
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        x = Integer.parseInt(tokenizer.nextToken());
        y = Integer.parseInt(tokenizer.nextToken());
    }
}