import java.io.*;
import java.util.*;

public class Main {

    private static int n, m;
    private static int[] comb;
    private static StringBuilder out = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        comb = new int[m];
        dfs(0, 1); 
        System.out.print(out);
    }

    private static void dfs(int depth, int start) {
        if (depth == m) {
            for (int i = 0; i < m; i++) out.append(comb[i]).append(' ');
            out.append('\n');
            return;
        }
        for (int i = start; i <= n; i++) {
            comb[depth] = i;
            dfs(depth + 1, i); 
        }
    }
}