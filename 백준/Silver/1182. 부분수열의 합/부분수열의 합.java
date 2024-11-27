import java.util.*;
import java.io.*;

public class Main {

    private static int n, s;
    private static int[] nums;
    private static int count = 0;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer(0, 0); 
        System.out.println(count);
    }

    // 부분수열을 탐색하는 함수
    private static void findAnswer(int index, int sum) {
        if (index == n) {
            return;
        }

        sum += nums[index];
        if (sum == s) {
            count++;
        }

        findAnswer(index + 1, sum - nums[index]);

        findAnswer(index + 1, sum);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        s = Integer.parseInt(tokenizer.nextToken());
        nums = new int[n];
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }
}