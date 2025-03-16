import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n;
    private static PriorityQueue<Integer> plus;
    private static PriorityQueue<Integer> minus;
    private static int oneCount;
    private static int zeroCount;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        plus = new PriorityQueue<>(Collections.reverseOrder());
        minus = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(input.readLine());
            if (num > 1) {
                plus.add(num);  // 1보다 큰 양수는 큐에 추가
            } else if (num == 1) {
                oneCount++;  // 1의 개수 카운트
            } else if (num == 0) {
                zeroCount++;  // 0의 개수 카운트
            } else {
                minus.add(num);  // 음수는 오름차순 정렬
            }
        }
    }

    private static void findAnswer() {
        int total = 0;
        total+=oneCount;

        while (plus.size() > 1) {
            int a = plus.poll();
            int b = plus.poll();
            total += a * b;
        }

        if (!plus.isEmpty()) {
            total += plus.poll();
        }

        while (minus.size() > 1) {
            int a = minus.poll();
            int b = minus.poll();
            total += a * b;
        }

        if(!minus.isEmpty()) {
            if (zeroCount % 2 != 1) {
                total += minus.poll();
            }
        }

        System.out.println(total);
    }
}
