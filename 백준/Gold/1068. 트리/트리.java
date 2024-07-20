import java.io.*;
import java.util.*;

public class Main {

    private static List<List<Integer>> tree;
    private static boolean[] deleted;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(input.readLine());

        tree = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tree.add(new ArrayList<>());
        }

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        int root = -1;
        for (int i = 0; i < n; i++) {
            int parent = Integer.parseInt(tokenizer.nextToken());
            if (parent == -1) {
                root = i; 
            } else {
                tree.get(parent).add(i);
            }
        }

        int delete = Integer.parseInt(input.readLine());
        deleted = new boolean[n];
        deleted[delete] = true;

        if (delete == root) {
            System.out.println(0);
            return;
        }

        int leafCount = countLeaves(root);
        System.out.println(leafCount);
    }

    private static int countLeaves(int node) {
        if (deleted[node]) {
            return 0;
        }

        List<Integer> children = tree.get(node);

        if (children.isEmpty()) {
            return 1;
        }

        int leafCount = 0;
        boolean hasChild = false;
        for (int child : children) {
            if (!deleted[child]) {
                hasChild = true;
                leafCount += countLeaves(child);
            }
        }

        if (!hasChild) {
            return 1;
        }

        return leafCount;
    }
}