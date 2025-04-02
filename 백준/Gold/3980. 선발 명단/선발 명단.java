import java.io.*;
import java.util.*;

public class Main {

    private static int testcase;
    private static StringBuilder answer = new StringBuilder();
    private static int[][] capacity = new int[11][11];
    private static int maxCapacitySum;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        testcase = Integer.parseInt(input.readLine());

        for (int t = 0; t < testcase; t++) {
            init(input);
            maxCapacitySum = 0;
            findPermutation(0, new boolean[11], 0);
            answer.append(maxCapacitySum).append("\n");
        }

        System.out.println(answer);
    }

    private static void init(BufferedReader input) throws IOException {
        for (int i = 0; i < 11; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            for (int j = 0; j < 11; j++) {
                capacity[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
    }

    private static void findPermutation(int position, boolean[] visited, int total) {
        if (position == 11) {
            maxCapacitySum = Math.max(maxCapacitySum, total);
            return;
        }

        for (int i = 0; i < 11; i++) {
            if (!visited[i] && capacity[i][position] > 0) {
                visited[i] = true;
                findPermutation(position + 1, visited, total + capacity[i][position]);
                visited[i] = false;
            }
        }
    }
}
