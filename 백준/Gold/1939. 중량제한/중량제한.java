import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n, m;
    private static List<int[]> edge;
    private static int[] fac;
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        edge = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());
            edge.add(new int[]{a, b, c});
        }
        edge.sort((e1, e2) -> Integer.compare(e2[2], e1[2]));
        fac = new int[2];
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < 2; i++) {
            fac[i] = Integer.parseInt(tokenizer.nextToken());
        }
        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }
    }

    private static void findAnswer() {
        int fac1 = fac[0];
        int fac2 = fac[1];

        for (int[] e : edge) {
            int u = e[0];
            int v = e[1];
            int w = e[2];
            union(u, v);
            if (find(fac1) == find(fac2)) {
                System.out.println(w);
                return;
            }
        }
    }

    private static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA < rootB) {
            parent[rootB] = rootA;
            return;
        }
        parent[rootA] = rootB;
    }

    private static int find(int a) {
        if (a == parent[a]) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }
}