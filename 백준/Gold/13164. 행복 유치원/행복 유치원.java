import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(input.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] heights = new int[n];
        st = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        // 간격 배열 만들기
        int[] gaps = new int[n - 1];
        for (int i = 1; i < n; i++) {
            gaps[i - 1] = heights[i] - heights[i - 1];
        }

        Arrays.sort(gaps);

        int totalCost = heights[n - 1] - heights[0];

        for (int i = 0; i < k - 1; i++) {
            totalCost -= gaps[n - 2 - i];
        }

        System.out.println(totalCost);
    }
}
