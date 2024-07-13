import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        int n = Integer.parseInt(tokenizer.nextToken());
        int s = Integer.parseInt(tokenizer.nextToken());
        int r = Integer.parseInt(tokenizer.nextToken());
        int[] damaged = new int[n + 1];
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < s; i++) {
            int miss = Integer.parseInt(tokenizer.nextToken());
            damaged[miss]++;
        }
        int[] reserve = new int[n + 1];
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < r; i++) {
            int x = Integer.parseInt(tokenizer.nextToken());
            reserve[x]++;
        }

        for (int i = 1; i <= n; i++) {
            if (damaged[i] == 1 && reserve[i] == 1) {
                damaged[i] = 0;
                reserve[i] = 0;
            }
        }

        for (int i = 1; i <= n; i++) {
            if (damaged[i] == 1) {
                if (i > 1 && reserve[i - 1] == 1) {
                    reserve[i - 1] = 0;
                    damaged[i] = 0;
                } else if (i < n && reserve[i + 1] == 1) {
                    reserve[i + 1] = 0;
                    damaged[i] = 0;
                }
            }
        }
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (damaged[i] == 1) {
                count++;
            }
        }
        System.out.println(count);
    }
}