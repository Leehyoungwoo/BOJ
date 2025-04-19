import java.io.*;
import java.util.*;

public class Main {

    private static int t;
    private static int n;
    private static int[] price;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(input.readLine());
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < t; i++) {
            init(input);
            long maxProfit = findAnswer();
            answer.append(maxProfit).append("\n");
        }

        System.out.println(answer);
    }

    private static void init(BufferedReader input) throws IOException {
        n = Integer.parseInt(input.readLine());
        price = new int[n];
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            price[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static long findAnswer() {
        long profit = 0;
        int maxPrice = 0;

        for (int i = n - 1; i >= 0; i--) {
            if (price[i] > maxPrice) {
                maxPrice = price[i];
            } else {
                profit += (maxPrice - price[i]);
            }
        }

        return profit;
    }
}
