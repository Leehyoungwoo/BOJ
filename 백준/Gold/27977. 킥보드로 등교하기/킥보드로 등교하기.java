import java.io.*;
import java.util.*;

public class Main {

    private static int l, n, k;
    private static int[] position;

    public static void main(String[] args) throws IOException {
        init();
        int answer = findAnswer();

        System.out.println(answer);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        l = Integer.parseInt(tokenizer.nextToken());
        n = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());
        position = new int[n + 1];
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            position[i] = Integer.parseInt(tokenizer.nextToken());
        }
        position[n] = l;
    }

    private static int findAnswer() {
        int left = 0;
        int right = l;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (!isValid(mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private static boolean isValid(int target) {
        int cur = 0;
        if (position[0] - cur > target) {
            return false;
        }
        int count = 0;
        for (int i = 0; i < position.length; i++) {
            if (i > 0 && position[i] - position[i - 1] > target) {
                return false;
            }
            if (position[i] - cur > target) {
                count++;
                cur = position[i - 1];
            }
            if (count > k) {
                return false;
            }
        }

        return count <= k;
    }
}