import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static int m;
    private static int l;
    private static int[] station;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        l = Integer.parseInt(tokenizer.nextToken());
        station = new int[n + 2];
        station[0] = 0;
        station[n + 1] = l;
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 1; i <= n; i++) {
            station[i] = Integer.parseInt(tokenizer.nextToken());
        }
        Arrays.sort(station);
        int left = 1;
        int right = l;
        int result = right;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (canPlaceStation(mid)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(result);
    }

    private static boolean canPlaceStation(int mid) {
        int count = 0;
        for (int i = 1; i < station.length; i++) {
            int gap = station[i] - station[i - 1];
            if (gap > mid) {
                count += (gap - 1) / mid;
            }
        }
        return count <= m;
    }
}