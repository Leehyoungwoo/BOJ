import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static char[] way;
    private static boolean[] visited;
    private static int[] destination;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        way = new char[n];
        visited = new boolean[n];
        destination = new int[n];
        Arrays.fill(destination, -1);
        String line = input.readLine();
        for (int i = 0; i < n; i++) {
            way[i] = line.charAt(i);
        }
    }

    private static void findAnswer() {
        boolean[] inStack = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, inStack);
            }
        }

        Set<Integer> answer = new HashSet<>();
        for (int i = 0; i < n; i++) {
            answer.add(destination[i]);
        }

        System.out.println(answer.size());
    }

    private static int dfs(int node, boolean[] inStack) {
        if (destination[node] != -1) {
            return destination[node];
        }

        if (inStack[node]) {
            return node;
        }

        visited[node] = true;
        inStack[node] = true;

        int next = (way[node] == 'E') ? node + 1 : node - 1;
        destination[node] = dfs(next, inStack);

        inStack[node] = false;

        return destination[node];
    }
}

