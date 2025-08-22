import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int[][] graph;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        graph = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
    }

    private static void findAnswer() {
        StringBuilder answer = new StringBuilder();
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                if (graph[i][k] == 0) continue;
                for (int j = 0; j < n; j++) {
                    if (graph[k][j] == 0) continue;
                    graph[i][j] = 1;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answer.append(graph[i][j] + " ");
            }
            answer.append("\n");
        }

        System.out.println(answer);
    }
}