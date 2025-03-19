import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static int G;
    private static List<Integer> weights;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        G = Integer.parseInt(input.readLine());
        weights = new ArrayList<>();
    }

    private static void findAnswer() {
        int left = 1; 
        int right = 2;

        while (right <= 100000) {
            int diff = right * right - left * left;

            if (diff == G) {
                weights.add(right);
                right++;
            } else if (diff < G) {
                right++;
            } else {
                left++;
            }
        }

        if (weights.isEmpty()) {
            System.out.println(-1);
        } else {
            for (int weight : weights) {
                System.out.println(weight);
            }
        }
    }
}