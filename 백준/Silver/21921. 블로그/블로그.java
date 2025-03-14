import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n, x;
    private static int[] nums;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        x = Integer.parseInt(tokenizer.nextToken());
        nums = new int[n];
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void findAnswer() {
        int left = 0;
        int right = x - 1;
        int sum = 0;
        int max = -1;
        for (int i = left; i <= right; i++) {
            sum += nums[i];
        }
        max = Math.max(max, sum);
        int count = 1;
        while (right < n - 1) {
            sum-=nums[left];
            left++;
            right++;
            sum+=nums[right];
            if (max < sum) {
                count = 1;
                max = sum;
            } else if (max == sum) {
                count++;
            }
        }

        if (max == 0) {
            System.out.println("SAD");
            return;
        }
        System.out.println(max);
        System.out.println(count);
    }
}