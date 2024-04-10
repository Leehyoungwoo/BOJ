import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int tc;
    private static int f;
    private static Map<String, Integer> idx;
    private static Queue<String[]> order;
    private static int[] parent;
    private static StringBuilder answer = new StringBuilder();
    private static Map<Integer, Integer> networkCount;


    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(input.readLine());
        for (int i = 0; i < tc; i++) {
            init(input);
            findAnswer();
        }

        System.out.println(answer);
    }

    private static void init(BufferedReader input) throws IOException {
        f = Integer.parseInt(input.readLine());
        idx = new HashMap<>();
        order = new LinkedList<>();
        networkCount = new HashMap<>();
        int cnt = 0;
        for (int i = 0; i < f; i++) {
            String[] member = input.readLine().split(" ");
            for (int j = 0; j < member.length; j++) {
                if (idx.get(member[j]) != null) {
                    continue;
                }
                idx.put(member[j], cnt);
                cnt++;
            }
            order.offer(member);
        }
        parent = new int[cnt];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
    }

    private static void findAnswer() {
        for (int i = 0; i < f; i++) {
            String[] member = order.poll();
            int firstIdx = idx.get(member[0]);
            int secondIdx = idx.get(member[1]);
            union(firstIdx, secondIdx);
            int network = networkCount.get(find(firstIdx));
            answer.append(network).append("\n");
        }
    }

    private static void union(int x, int y) {
        int xParent = find(x);
        int yParent = find(y);
        if (xParent != yParent) {
            parent[yParent] = xParent;
            networkCount.put(xParent, networkCount.getOrDefault(xParent, 1) + networkCount.getOrDefault(yParent, 1));
        }
    }

    private static int find(int x) {
        if (parent[x] == x) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }
}