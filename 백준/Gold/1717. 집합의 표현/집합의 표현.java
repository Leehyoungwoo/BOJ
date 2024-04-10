import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n;
    private static int m;
    private static int[] num;
    private static Queue<int[]> que = new LinkedList<>();
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
        System.out.println(sb);
    }

    private static void findAnswer() {
        for (int i = 0; i < m; i++) {
            int[] cur = que.poll();
            int order = cur[0];
            int first = cur[1];
            int second = cur[2];
            if (order == 0) {
                union(first, second);
                continue;
            }

            if (order == 1) {
                int x1 = find(first);
                int x2 = find(second);
                if (x1 == x2) {
                    sb.append("YES").append("\n");
                } else {
                    sb.append("NO").append("\n");
                }
            }
        }
    }

    private static void union(int first, int second) {
        int aParent = find(first);
        int bParent = find(second);
        if (first <= second) {
            num[bParent] = aParent;
            return;
        }

        num[aParent] = bParent;
    }

    private static int find(int first) {
        if (first == num[first]) {
            return first;
        }

        return num[first] = find(num[first]);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        num = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            num[i] = i;
        }

        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int order = Integer.parseInt(tokenizer.nextToken());
            int parrent = Integer.parseInt(tokenizer.nextToken());
            int son = Integer.parseInt(tokenizer.nextToken());
            que.offer(new int[]{order, parrent, son});
        }
    }
}