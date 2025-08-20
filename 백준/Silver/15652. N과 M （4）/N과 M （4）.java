import java.io.*;
import java.util.*;

public class Main {

    private static int n, m;
    private static StringBuilder answer;
    private static Set<String> set;
    private static int[] comb;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        answer = new StringBuilder();
        comb = new int[m];
        set = new HashSet<>();
    }

    private static void findAnswer() {
        dfs(0, 1);
        List<String> list = new ArrayList<>(set);
        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            sb.append(str);
        }
        System.out.println(sb);
    }

    private static void dfs(int idx, int start) {
        if (idx == m) {
            answer = new StringBuilder();
            for (int i = 0; i < comb.length; i++) {
                answer.append(comb[i]).append(" ");
            }
            answer.append("\n");
            set.add(answer.toString());
            return;
        }

        if (start == n + 1) {
            return;
        }

        comb[idx] = start;
        dfs(idx + 1, start);
        dfs(idx + 1, start + 1);
        dfs(idx, start + 1);
    }
}