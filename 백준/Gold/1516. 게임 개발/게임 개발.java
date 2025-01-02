import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int[] inDegree;
    private static List<List<Integer>> adjList;
    private static int[] time;
    private static int[] completeTime;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
        Arrays.stream(completeTime).skip(1).forEach(System.out::println);
    }

    private static void findAnswer() {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < completeTime.length; i++) {
            completeTime[i] = time[i];
        }
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                priorityQueue.add(i);
            }
        }

        while (!priorityQueue.isEmpty()) {
            int cur = priorityQueue.poll();
            for (Integer next : adjList.get(cur)) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    priorityQueue.add(next);
                }
                completeTime[next] = Math.max(completeTime[next], completeTime[cur] + time[next]);
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        time = new int[n + 1];
        completeTime = new int[n + 1];
        inDegree = new int[n + 1];
        adjList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 1; i <= n; i++) {
            String line = input.readLine();
            String[] tokens = line.split(" ");
            time[i] = Integer.parseInt(tokens[0]);
            int idx = 1;
            while (!tokens[idx].equals("-1")) {
                String t = tokens[idx];
                adjList.get(Integer.parseInt(t)).add(i);
                inDegree[i]++;
                idx++;
            }
        }
    }
}