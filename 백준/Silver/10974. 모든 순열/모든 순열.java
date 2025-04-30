import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static StringBuilder answer;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        answer = new StringBuilder();
    }

    private static void findAnswer() {
        dfs(0, new int[n], new boolean[n + 1]);
        System.out.println(answer);
    }

    private static void dfs(int idx, int[] per, boolean[] visited) {
        if (idx == n) {
            for (int i = 0; i < per.length; i++) {
                answer.append(per[i]).append(" ");
            }
            answer.append("\n");
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                per[idx] = i;
                dfs(idx + 1, per, visited);
                visited[i] = false;
            }
        }
    }
}