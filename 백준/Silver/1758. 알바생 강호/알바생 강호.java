import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    private static int N;
    private static Integer[] tips;
    private static long maxTip;

    public static void main(String[] args) throws IOException {
        init();
        Arrays.sort(tips, Collections.reverseOrder());
        maxTip = findTotalTip();
        System.out.println(maxTip);
    }



    private static long findTotalTip() {
        long sum = 0;

        for (int i = 0; i < N; i++) {
            int eachTip = tips[i] - i;
            if (eachTip <= 0) {
                continue;
            }
            sum += eachTip;
        }
        return sum;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tips = new Integer[N];

        for (int i = 0; i < N; i++) {
            tips[i] = Integer.parseInt(br.readLine());
        }
    }
}