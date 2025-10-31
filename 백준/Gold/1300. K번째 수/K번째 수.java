import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int k;

    public static void main(String[] args) throws IOException {
        init();
        long answer = findAnswer();

        System.out.println(answer);
    }

    // 100000
    private static long findAnswer() {
        // 배열을 직접 만들수는 없고
        // 어떤 값을 제시했을떄
        // 그 수보다 작은 수가 k개 있으면  2보다 작은게 1개 있으면 2번째 수인거마냥 mid보다 작은게 k개 있으면 k번째 수인거지?
        // 로어 바운드로 첫번째 등장하는 k면 그게 k번쨰 수
        long answer = 0;
        long left = 0;
        long right = n * (long) n;
        while (left <= right) {
            // 내가 찾는 값은 mid가 k번쨰인가잖아?
            // answer = mid로 하고 싶은거니까
            long mid = left + (right - left) / 2;
            if (findCount(mid, n) >= k) {
                // 답에 포함했으니 넘기고
                answer = mid;
                right = mid - 1;
            } else {
                // 답이 전부 아닌 영역이니까 mid 포함X
                left = mid + 1;
            }
        }

        return answer;
    }

    private static long findCount(long mid, int n) {
        // mid보다 작은 수의 개수를 구하는거지
//        행 1: 1×1, 1×2, 1×3, …, 1×n
//        행 2: 2×1, 2×2, 2×3, …, 2×n
//        행 3: 3×1, 3×2, 3×3, …, 3×n
//         ...
//        행 i: i×1, i×2, i×3, …, i×n
        long count = 0;
        // 각 행에서 mid보다 아래인걸 합친다고 생각하면 되지 않나?
        // mid값이 너무 커서 i * n을 넘을 수 있지, 그런 경우에는 n개 전부 작은거지
        // 그리고 mid / i하면 3.5 이런식으로 나오면 3개가 mid보다 작은거지 i행에서는
        // i가 mid라고 치면 i행에서는 1열 말고는 다 mid보다 크잖아
        for (int i = 1; i <= n; i++) {
            count += Math.min(mid / i, n);
        }

        return count;
    }

    private static int findLowAnswer() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                pq.offer(i * j);
            }
        }
        int value = 0;
        while (k-- > 0) {
            value = pq.poll();
        }

        return value;
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        k = Integer.parseInt(input.readLine());
    }
}