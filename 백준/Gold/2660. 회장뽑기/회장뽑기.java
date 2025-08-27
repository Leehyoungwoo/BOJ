import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int[][] dist;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new  BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        dist = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][i] = 0;
        }
        String line;
        while (!(line = input.readLine()).equals("-1 -1")) {
            String[] str =  line.split(" ");
            int a = Integer.parseInt(str[0]);
            int b = Integer.parseInt(str[1]);
            dist[a][b] = 1;
            dist[b][a] = 1;
        }
    }

    private static void findAnswer() {
        // 거리를 구해서 플로이드워셜로, 그리고나서 점수의 최솟값을 찾고 최솟값에 해당하는 노드를 리스트에 담아서 출력
        StringBuffer answer = new StringBuffer();
        floydWarshall();
        // 최소 점수 찾기
        int min = Integer.MAX_VALUE;
        int[] score = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 1; j <= n; j++) {
                if (dist[i][j] == Integer.MAX_VALUE) {
                    continue;
                }
                max = Math.max(max, dist[i][j]);
                score[i] = max;
            }
            min = Math.min(min, max);
        }
        List<Integer> candidates = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (score[i] == min) {
                candidates.add(i);
            }
        }

        answer.append(min).append(" ").append(candidates.size()).append("\n");

        for (int i = 0; i < candidates.size(); i++) {
            answer.append(candidates.get(i)).append(" ");
        }

        System.out.println(answer);
    }

    private static void floydWarshall() {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                if (dist[i][k] == Integer.MAX_VALUE) {
                    continue;
                }
                for (int j = 1; j <= n; j++) {
                    if (dist[k][j] == Integer.MAX_VALUE) {
                        continue;
                    }
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }
}