import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static int m;
    private static int[] nums;
    private static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String[] s = input.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);
        nums = new int[m];
        dfs(0, new boolean[n + 1]);
        System.out.println(answer);
    }

    private static void dfs(int idx, boolean[] visited) {
        if (idx == m) {
            for(Integer n : nums) {
                answer.append(n).append(" ");
            }
            answer.append("\n");
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                nums[idx] = i;
                visited[i] = true;
                dfs(idx + 1, visited);
                visited[i] = false;
            }
        }
    }
}