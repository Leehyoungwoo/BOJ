import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n;
    private static int m;
    private static int[] parent;
    private static Queue<int[]> order = new LinkedList<>();
    private static String plan;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        m = Integer.parseInt(input.readLine());
        parent = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < n; i++) {
            String[] s = input.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                int linked = Integer.parseInt(s[j]);
                if (linked == 1) {
                    union(i + 1, j + 1);
                }
            }
        }

        plan = input.readLine();
    }

    public static void union(int x, int y) {
        int xParent = find(x);
        int yParent = find(y);
        if (xParent <= yParent) {
            parent[yParent] = xParent;
            return;
        }
        parent[xParent] = yParent;
    }

    public static int find(int x) {
        if (x == parent[x]) {
            return parent[x];
        }

        return parent[x] = find(parent[x]);
    }

    private static void findAnswer() {
        boolean flag = true;
        String[] way = plan.split(" ");
        for (int i = 0; i < way.length - 1; i++) {
            int start = find(Integer.parseInt(way[i]));
            int end = find(Integer.parseInt(way[i + 1]));
            if (start != end) {
                flag = false;
                break;
            }
        }

        if (flag) {
            System.out.println("YES");
            return;
        }
        System.out.println("NO");
    }
}