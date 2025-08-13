import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int[] nums;
    private static int m;
    private static int[][] query;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        nums = new int[n];
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(tokenizer.nextToken());
        }

        m = Integer.parseInt(input.readLine());
        query = new int[m][2];
        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            query[i][0] = Integer.parseInt(tokenizer.nextToken());
            query[i][1] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void findAnswer() {
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int start = query[i][0] - 1;
            int end = query[i][1] - 1;
            findEachAnswer(start, end, answer);
        }

        System.out.println(answer);
    }

    private static void findEachAnswer(int left, int right, StringBuilder answer) {
        while (left <= right) {
            if (nums[left] != nums[right]) {
                answer.append(0).append("\n");
                return;
            }
            left++;
            right--;
        }

        answer.append(1).append("\n");
    }
}