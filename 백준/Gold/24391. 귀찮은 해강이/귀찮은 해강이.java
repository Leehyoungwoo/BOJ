import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static int m;
    private static int[] classes;
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
        classes = new int[n];
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < m; i++) {
            tokenizer =  new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            union(a, b);
        }
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < classes.length; i++) {
            classes[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void findAnswer() {
        int inCount = 0;
        int prev = -1;
        for (int i = 0; i < classes.length; i++) {
            int no = classes[i];
            if (prev == -1) {
                prev = find(no);
                continue;
            }

            if (prev == find(no)) {
                continue;
            }

            inCount++;
            prev = find(no);
        }

        System.out.println(inCount);
    }

    private static void union(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);
        if (aParent <= bParent) {
            parent[bParent] = aParent;
            return;
        }

        parent[aParent] = bParent;
    }

    private static int find(int a) {
        if (a ==  parent[a]) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }
}