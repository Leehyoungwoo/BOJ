import java.io.*;
import java.util.*;

public class Main {

    private static int n, m;
    private static List<Integer> nums;
    private static int[] comb;
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
        tokenizer = new StringTokenizer(input.readLine());
        nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nums.add(Integer.parseInt(tokenizer.nextToken()));
        }
        Collections.sort(nums);
        comb = new int[m];
        answer = new StringBuilder();
    }

    private static void findAnswer() {
        dfs(0,  new boolean[nums.size()]);

        System.out.println(answer);
    }

    private static void dfs(int idx, boolean[] visited) {
        if (idx == m) {
            for (int i = 0; i < comb.length; i++) {
                answer.append(nums.get(comb[i])).append(" ");
            }
            answer.append("\n");
            return;
        }

        int used = -1;
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            int cur = nums.get(i);
            if (used != -1 && cur == used) {
                continue;
            }
            visited[i] = true;
            comb[idx] = i;
            dfs(idx + 1, visited);
            visited[i] = false;
            used = cur;
        }
    }
}