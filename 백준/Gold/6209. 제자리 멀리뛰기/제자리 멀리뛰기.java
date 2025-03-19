import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int d;
    private static int[] num;
    private static int n;
    private static int m;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        d = Integer.parseInt(tokenizer.nextToken());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        num = new int[n + 2];

        num[0] = 0;
        num[n + 1] = d;

        for (int i = 1; i <= n; i++) {
            num[i] = Integer.parseInt(input.readLine());
        }
        Arrays.sort(num);
    }

    private static void findAnswer() {
        int left = 0;
        int right = d;
        int result = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canRemove(mid)) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(result);
    }

    private static boolean canRemove(int minDiff) {
        int count = 0;
        int prev = 0;
        for (int i = 1; i <= n + 1; i++) {
            if (num[i] - prev < minDiff) {
                count++;
                if (count > m) {
                    return false;
                }
            } else {
                prev = num[i];
            }
        }

        return count <= m;
    }
}