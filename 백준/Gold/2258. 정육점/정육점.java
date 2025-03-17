import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n;
    private static int m;
    private static List<int[]> meats;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String[] line = input.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        m = Integer.parseInt(line[1]);
        meats = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            line = input.readLine().split(" ");
            meats.add(new int[]{Integer.parseInt(line[0]), Integer.parseInt(line[1])});
        }
    }

    private static void findAnswer() {
        meats.sort(Comparator.comparingInt((int[] a) -> a[1])
                .thenComparingInt(a -> -a[0]));

        int totalWeight = 0;
        int totalPrice = 0;
        int minCost = Integer.MAX_VALUE;
        int prevPrice = -1;
        int accumulatedPrice = 0;

        for (int[] meat : meats) {
            int weight = meat[0];
            int price = meat[1];

            totalWeight += weight;
            if (prevPrice == price) {
                accumulatedPrice += price;
            } else {
                accumulatedPrice = price;
            }
            prevPrice = price;

            if (totalWeight >= m) {
                minCost = Math.min(minCost, accumulatedPrice);
            }
        }

        if (totalWeight < m) {
            System.out.println(-1);
        } else {
            System.out.println(minCost);
        }
    }
}
