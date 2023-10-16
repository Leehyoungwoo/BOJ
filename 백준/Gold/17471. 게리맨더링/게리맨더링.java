import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N;
    private static int[] people;
    private static List<List<Integer>> graph;
    private static List<Integer> aPowerSet;
    private static List<Integer> bPowerSet;
    private static int minDifPeople;

    public static void main(String[] args) throws IOException {
        init();
        findPowerset(1);
        System.out.println(minDifPeople == Integer.MAX_VALUE ? -1 : minDifPeople);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(input.readLine());
        people = new int[N + 1];
        graph = new ArrayList<>();
        aPowerSet = new ArrayList<>();
        bPowerSet = new ArrayList<>();
        minDifPeople = Integer.MAX_VALUE;

        for (int i = 0; i < N + 1; i++) {
            List<Integer> adjList = new ArrayList<>();
            graph.add(adjList);
        }

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 1; i < people.length; i++) {
            people[i] = Integer.parseInt(tokenizer.nextToken());
        }

        for (int i = 1; i < N + 1; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int n = Integer.parseInt(tokenizer.nextToken());
            for (int j = 0; j < n; j++) {
                int v = Integer.parseInt(tokenizer.nextToken());
                graph.get(i).add(v);
                graph.get(v).add(i);
            }
        }
    }

    private static void findPowerset(int start) {
        if (start == N + 1) {
            makeBPowerSer();
            if (aPowerSet.size() != 0 && aPowerSet.size() != N && isValidArea()) {
                int diff = findDiffPeople();
                minDifPeople = Math.min(minDifPeople, diff);
            }

            return;
        }

        aPowerSet.add(start);
        findPowerset(start + 1);
        aPowerSet.remove(aPowerSet.indexOf(start));
        findPowerset(start + 1);
    }

    private static void makeBPowerSer() {
        bPowerSet.clear();
        for (int i = 1; i <= N; i++) {
            if (aPowerSet.contains(i)) {
                continue;
            }

            bPowerSet.add(i);
        }
    }

    private static int findDiffPeople() {
        int aArea = 0;

        for (int num : aPowerSet) {
            aArea += people[num];
        }

        int bArea = totalPeople() - aArea;

        return Math.abs(aArea - bArea);
    }

    private static int totalPeople() {
        int sum = 0;

        for (int i = 1; i <= N; i++) {
            sum += people[i];
        }

        return sum;
    }

    private static boolean isValidArea() {
        boolean valid = false;
        /// powerSet 검사
        int aCnt = bfs(aPowerSet);
        /// 나머지 검사
        int bCnt = bfs(bPowerSet);
        /// 합쳐서 N과 같으면 valid = true;
        if (aCnt + bCnt == N) {
            valid = true;
        }

        return valid;
    }

    private static int bfs(List<Integer> powerSet) {
        int cnt = 0;
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> que = new LinkedList<>();
        que.offer(powerSet.get(0));
        visited[powerSet.get(0)] = true;

        while (!que.isEmpty()) {
            int cur = que.poll();
            cnt++;

            for (int nextCur : graph.get(cur)) {
                if (!visited[nextCur] && powerSet.contains(nextCur)) {
                    que.offer(nextCur);
                    visited[nextCur] = true;
                }
            }
        }

        return cnt;
    }
}