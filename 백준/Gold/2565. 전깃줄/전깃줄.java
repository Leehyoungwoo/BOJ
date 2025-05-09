import java.io.*;
import java.util.*;

public class Main {
    static class Wire {
        int a, b;

        Wire(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        Wire[] wires = new Wire[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            wires[i] = new Wire(a, b);
        }
        Arrays.sort(wires, Comparator.comparingInt(w -> w.a));

        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int lis = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (wires[j].b < wires[i].b) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            lis = Math.max(lis, dp[i]);
        }

        System.out.println(n - lis);
    }
}