import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[] parent;
    static long originalWeight;
    static long newWeight;
    static PriorityQueue<int[]> edges = new PriorityQueue<>((a, b) -> {
        return a[2] - b[2];
    });

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges.add(new int[] {from ,to, weight});
            originalWeight += weight;
        }

        int cnt = 0;
        while (!edges.isEmpty()) {
            int[] current = edges.poll();
            int from = current[0];
            int to = current[1];
            int weight = current[2];

            if (find(from) != find(to)) {

                newWeight += weight;
                union(from, to);
                cnt++;

                if (cnt == N - 1) break;
            }
        }

        if (cnt != N - 1) System.out.println(-1);
        else System.out.println(originalWeight - newWeight);
    }

    public static void union(int a, int b){
        int aParent = find(a);
        int bParent = find(b);

        if (aParent < bParent) {
            parent[bParent] = aParent;
        } else if (bParent < aParent) {
            parent[aParent] = bParent;
        }
    }

    public static int find(int target) {
        if (parent[target] == target) return target;
        else return parent[target] = find(parent[target]);
    }
}