import java.io.*;
import java.util.*;

public class Main {

    private static int n, k, s;
    private static boolean[][] isp;
    private static List<int[]> inputs;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());
        isp = new boolean[n + 1][n + 1];
        for (int i = 0; i < k; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            isp[a][b] = true;
        }

        inputs = new ArrayList<>();
        s = Integer.parseInt(input.readLine());
        for (int i = 0; i < s; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            inputs.add(new int[]{a, b});
        }
    }

    private static void findAnswer() {
        StringBuilder answer = new StringBuilder();
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    isp[i][j] = isp[i][j] || (isp[i][k] && isp[k][j]);
                }
            }
        }
        for (int idx = 0; idx < inputs.size(); idx++) {
            int s = inputs.get(idx)[0];
            int e = inputs.get(idx)[1];
            if (!isp[s][e] && !isp[e][s]) {
                answer.append(0 + " ");
            } else if (isp[s][e]) {
                answer.append(-1 + " ");
            } else if (isp[e][s]) {
                answer.append(1 + " ");
            }
            answer.append("\n");
        }

        System.out.println(answer);
    }
}