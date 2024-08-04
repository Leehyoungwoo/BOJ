import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(input.readLine());

        int MAX_N = 1_000_000;

        long[] f = new long[MAX_N + 1];

        long[] g = new long[MAX_N + 1];

        for (int i = 1; i <= MAX_N; i++) {
            for (int j = i; j <= MAX_N; j += i) {
                f[j] += i;
            }
        }

        for (int i = 1; i <= MAX_N; i++) {
            g[i] = g[i - 1] + f[i];
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(input.readLine());
            answer.append(g[n]).append("\n");
        }

        System.out.println(answer);
    }
}