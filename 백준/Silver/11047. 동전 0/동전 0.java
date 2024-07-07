import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int K = Integer.parseInt(line[1]);
        int[] coin = new int[N];
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }
        br.close();

        for (int i = N-1; i >= 0; i--) {
            if (coin[i] <= K) {
                cnt += K / coin[i];
                K %= coin[i];
            }
        }
        System.out.println(cnt);
    }
}