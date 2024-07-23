import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 누적합
        long[] sum = new long[N];
        sum[0] = arr[0];
        for (int i = 1; i < N; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }

        // K * (i + 1) - sum[i] 구하기
        long avg = 0, sub;
        Map<Long, Integer> map = new HashMap<>();
        Integer count;
        long answer = 0;
        for (int i = 0; i < N; i++) {
            avg += K;

            sub = avg - sum[i];

            if(sub == 0) answer++;

            count = map.get(sub);
            if(count != null) {
                answer += count;
                map.put(sub, count + 1);
            }
            else {
                map.put(sub, 1);
            }
        }

        System.out.println(answer);
    }
}