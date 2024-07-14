import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(input.readLine());
        int[][] matrix = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            matrix[i][0] = Integer.parseInt(tokenizer.nextToken());
            matrix[i][1] = Integer.parseInt(tokenizer.nextToken());
        }
        Arrays.sort(matrix, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        int sum = 0;
        for (int[] problem : matrix) {
            int deadline = problem[0];
            int ramen = problem[1];
            priorityQueue.offer(ramen);

            if (priorityQueue.size() > deadline) {
                priorityQueue.poll();
            }
        }
        while (!priorityQueue.isEmpty()) {
            sum += priorityQueue.poll();
        }

        System.out.println(sum);
    }
}