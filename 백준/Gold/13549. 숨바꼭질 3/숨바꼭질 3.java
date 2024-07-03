import java.util.*;
import java.io.*;

public class Main {

    private static int[] moves = { -1, 1, 2};
    private static int n;
    private static int k;
    private static int[] distance;
    private final static int MAX = 100_001;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String[] s = input.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        k = Integer.parseInt(s[1]);
        distance = new int[MAX];
        Arrays.fill(distance, Integer.MAX_VALUE);
    }

    private static void findAnswer() {
        Queue<Integer> que = new LinkedList<>();
        distance[n] = 0;
        que.add(n);
        while (!que.isEmpty()) {
            int cur = que.poll();
            int next = cur * 2;
            if (isInRange(next) && distance[next] > distance[cur]) {
                distance[next] = distance[cur];
                que.add(next);
            }
            next = cur - 1;
            if (isInRange(next) && distance[next] > distance[cur] + 1) {
                distance[next] = distance[cur] + 1;
                que.add(next);
            }
            next = cur + 1;
            if (isInRange(next) && distance[next] > distance[cur] + 1) {
                distance[next] = distance[cur] + 1;
                que.add(next);
            }
        }
        System.out.println(distance[k]);
    }

    private static boolean isInRange(int next) {
        return 0 <= next && next < MAX;
    }
}