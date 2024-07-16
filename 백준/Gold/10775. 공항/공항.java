import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int g;
    private static int p;
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        init();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        g = Integer.parseInt(input.readLine());
        p = Integer.parseInt(input.readLine());
        parent = new int[g + 1];

        for (int i = 0; i <= g; i++) {
            parent[i] = i;
        }

        int result = 0;

        for (int i = 0; i < p; i++) {
            int gi = Integer.parseInt(input.readLine());
            int root = find(gi);

            if (root == 0) {
                break;
            }

            union(root, root - 1);
            result++;
        }

        System.out.println(result);
    }

    private static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    private static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX <= rootY) {
            parent[rootY] = rootX;
            return;
        }
        parent[rootX] = rootY;
    }
}