import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static int m;
    private static int[] nums;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        nums = new int[n];
        tokenizer = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void findAnswer() {
        int minVal = Arrays.stream(nums).min().getAsInt();
        int maxVal = Arrays.stream(nums).max().getAsInt();
        System.out.println(binarySearch(0, maxVal - minVal));
    }

    private static int binarySearch(int left, int right) {
        int result = right;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canDivide(mid)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

    private static boolean canDivide(int maxScore) {
        int count = 1; // 첫번쨰 구간
        int minVal = nums[0];
        int maxVal = nums[0];
        for (int i = 1; i < n; i++) {
            minVal = Math.min(minVal, nums[i]);
            maxVal = Math.max(maxVal, nums[i]);
            if (maxVal - minVal > maxScore) {
                count++;
                minVal = nums[i];
                maxVal = nums[i];
            }
        }

        return count <= m;
    }
}
