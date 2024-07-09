import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n;
    private static int k;
    private static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        k = Integer.parseInt(input.readLine());
        nums = new int[n];
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(tokenizer.nextToken());
        }
        Arrays.sort(nums);
        int[] distance = new int[n - 1];
        for (int i = 1; i < n; i++) {
            distance[i - 1] = nums[i] - nums[i - 1];
        }
        Arrays.sort(distance);
        int answer = 0;
        for (int i = 0; i < n - k; i++) {
            answer += distance[i];
        }
        System.out.println(answer);
    }
}