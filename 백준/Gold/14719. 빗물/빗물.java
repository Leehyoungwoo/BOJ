import java.io.*;
import java.util.*;

public class Main {

    private static int h, w;
    private static int[] height;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        h = Integer.parseInt(tokenizer.nextToken());
        w = Integer.parseInt(tokenizer.nextToken());
        height = new int[w];
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < w; i++) {
            height[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void findAnswer() {
        int[] leftMax = new int[w];
        int[] rightMax = new int[w];

        leftMax[0] = height[0];
        for (int i = 1; i < w; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        rightMax[w - 1] = height[w - 1];
        for (int i = w - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int total = 0;
        for (int i = 0; i < w; i++) {
            int water = Math.min(leftMax[i], rightMax[i]) - height[i];
            if (water > 0) {
                total += water;
            }
        }

        System.out.println(total);
    }
}