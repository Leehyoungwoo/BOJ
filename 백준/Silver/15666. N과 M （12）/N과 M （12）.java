import java.io.*;
import java.util.*;

public class Main {

    private static int n, m;
    private static int[] comb;
    private static int[] nums;
    private static StringBuilder answer;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        comb = new int[m];
        nums = new int[n];
        answer = new StringBuilder();
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(tokenizer.nextToken());
        }
        Arrays.sort(nums);
    }

    private static void findAnswer() {
        dfs(0, 0);

        System.out.println(answer);
    }

    private static void dfs(int idx, int start) {
        if (idx == m) {
            for (int i = 0; i < comb.length; i++) {
                answer.append(nums[comb[i]]).append(" ");
            }
            answer.append("\n");
            return;
        }

        int used = -1;
        for (int i = start; i < n; i++) {
            int cur = nums[i];
            if (cur != -1 && used == cur) {
                continue;
            }
            comb[idx] = i;
            dfs(idx + 1, i);
            used = cur;
        }
    }
}