import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int n, k;
    private static int[] nums;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());
        nums = new int[n];
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void findAnswer() {
        int left = 0;
        int right = Arrays.stream(nums).sum();
        int result = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canDivide(mid)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(result);
    }

    private static boolean canDivide(int target) {
        int count = 1;
        int divSum = 0;
        for (int i = 0; i < nums.length; i++) {
            divSum+=nums[i];
            if (divSum > target) {
                count++;
                divSum = 0;
            }
            if (count > k) {
                return false;
            }
        }

        return true;
    }
}
