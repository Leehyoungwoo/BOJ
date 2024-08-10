import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n;
    private static int[] nums;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void findAnswer() {
        int answer = 0;
        for (int i = 0; i < nums.length; i++) {
            int left = 0;
            int right = nums.length - 1;
            while (left < right) {
                if (left == i) {
                    left++;
                    continue;
                }
                if (right == i) {
                    right--;
                    continue;
                }

                if (nums[left] + nums[right] == nums[i]) {
                    answer++;
                    break;
                } else if (nums[left] + nums[right] > nums[i]) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        System.out.println(answer);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        nums = new int[n];
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(tokenizer.nextToken());
        }
        Arrays.sort(nums);
    }
}