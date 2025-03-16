import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int n;
    private static List<int[]> dishes;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        dishes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(input.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            dishes.add(new int[]{A, B});
        }
    }

    private static void solve() {
        // **B 기준으로 정렬**
        dishes.sort(Comparator.comparingInt(a -> a[1]));

        // **suffix minimum: i~n까지 A 최소값 저장**
        int[] minA = new int[n];
        minA[n - 1] = dishes.get(n - 1)[0];
        for (int i = n - 2; i >= 0; i--) {
            minA[i] = Math.min(minA[i + 1], dishes.get(i)[0]);
        }

        // **정답 저장할 배열**
        long[] ans = new long[n];
        Arrays.fill(ans, Long.MAX_VALUE);

        // **전략 1: 현재 인덱스가 i라면, i-1까지 B 합을 누적하고 minA[i]를 더함**
        long sumB = 0;
        for (int i = 0; i < n; i++) {
            ans[i] = Math.min(ans[i], sumB + minA[i]);
            sumB += dishes.get(i)[1];
        }

        // **전략 2: A - B 차이가 가장 작은 물건을 우선 선택하는 최소 힙**
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        sumB = 0;
        for (int i = 0; i < n; i++) {
            sumB += dishes.get(i)[1];
            pq.offer(dishes.get(i)[0] - dishes.get(i)[1]);
            ans[i] = Math.min(ans[i], sumB + pq.peek()); // 가장 이득이 큰 A 선택
        }

        // **정답 출력**
        StringBuilder sb = new StringBuilder();
        for (long an : ans) {
            sb.append(an).append("\n");
        }
        System.out.print(sb);
    }
}
