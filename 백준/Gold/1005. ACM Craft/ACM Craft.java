import java.io.*;
import java.util.*;

public class Main {

    private static int testcase;
    private static int n;
    private static int k;
    private static int[] time;
    private static int[] inDegree;
    private static int w;
    private static List<List<Integer>> graph;
    private static int[] completionTime;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        testcase = Integer.parseInt(input.readLine());

        for (int i = 0; i < testcase; i++) {
            init(input);
            findAnswer();
            answer.append(completionTime[w]).append("\n");
        }
        System.out.println(answer);
    }

    private static void findAnswer() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        Arrays.fill(completionTime, Integer.MIN_VALUE);

        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
                completionTime[i] = time[i];
            }
        }

        while (!queue.isEmpty()) {
            int currentBuilding = queue.poll();
            for (int nextBuilding : graph.get(currentBuilding)) {
                completionTime[nextBuilding] = Math.max(completionTime[nextBuilding], completionTime[currentBuilding] + time[nextBuilding]);
                inDegree[nextBuilding]--;
                if (inDegree[nextBuilding] == 0) {
                    queue.offer(nextBuilding);
                }
            }
        }
    }

    private static void init(BufferedReader input) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());
        time = new int[n + 1];
        inDegree = new int[n + 1];
        completionTime = new int[n + 1];
        graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 1; i <= n; i++) {
            time[i] = Integer.parseInt(tokenizer.nextToken());
        }

        for (int i = 0; i < k; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            graph.get(a).add(b);
            inDegree[b]++;
        }

        w = Integer.parseInt(input.readLine());
    }
}