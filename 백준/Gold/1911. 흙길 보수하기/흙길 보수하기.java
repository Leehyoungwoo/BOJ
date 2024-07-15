import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static int l;
    private static int[][] water;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(input.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(input.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            pq.offer(new int[]{start, end});
        }
        int cur = 0;
        int count = 0;
        while (!pq.isEmpty()) {
            int[] curW = pq.poll();
            int curStart = curW[0];
            int curEnd = curW[1];
            if (cur < curStart) {
                cur = curStart;
            }
            while (cur < curEnd) {
                cur+=l;
                count++;
            }
        }

        System.out.println(count);
    }
}