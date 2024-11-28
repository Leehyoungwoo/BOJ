import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long L = Long.parseLong(st.nextToken());
        long W = Long.parseLong(st.nextToken());
        long H = Long.parseLong(st.nextToken());

        double min = Math.min(L, Math.min(W, H));
        double left = 0;
        double right = min;

        for (int i = 0; i < 5000; ++i) {
            double mid = (left + right) / 2;
            if ((long)(L / mid) * (long)(W / mid) * (long)(H / mid) < N) {
                right = mid;
            } else {
                left = mid;
            }
        }

        System.out.printf("%.9f%n", left);
    }
}