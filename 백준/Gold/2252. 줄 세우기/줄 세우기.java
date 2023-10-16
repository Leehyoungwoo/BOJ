import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static int m;
    private static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    private static Queue<Integer> queue = new LinkedList<>();
    private static ArrayList<Integer> indegrees = new ArrayList<>(); 

    public static void main(String[] args) throws IOException {
        init();
        lineUp();
    }

    private static void lineUp() {
        for (int i = 1; i <= n; i++) {
            if (indegrees.get(i) == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            System.out.print(cur + " ");

            ArrayList<Integer> nextVertices = graph.get(cur);

            for (int next : nextVertices) {
                indegrees.set(next, indegrees.get(next) - 1);

                if (indegrees.get(next) == 0) {
                    queue.add(next);
                }
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
            indegrees.add(0);
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer tokenizer2 = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokenizer2.nextToken());
            int b = Integer.parseInt(tokenizer2.nextToken());
            graph.get(a).add(b);
            indegrees.set(b, indegrees.get(b) + 1); 
        }
    }
}