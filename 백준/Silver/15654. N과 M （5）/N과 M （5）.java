import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int m;
    private static int[] arr;
    private static int[] inputs;

    public static void main(String[] args) throws IOException {
        init();
        dfs(0, new boolean[n]);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());

        inputs = new int[n];
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            inputs[i] = Integer.parseInt(tokenizer.nextToken());
        }
        arr = new int[m];
        Arrays.sort(inputs);
    }

    private static void dfs(int idx, boolean[] visited) {
        if (idx == m) {
            printArray(arr);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                arr[idx] = inputs[i];
                visited[i] = true;
                dfs(idx + 1, visited);
                visited[i] = false;
            }
        }
    }

    private static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}