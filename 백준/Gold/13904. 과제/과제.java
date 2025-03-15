import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n;
    private static List<int[]> inputs;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        inputs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] s = input.readLine().split(" ");
            inputs.add(new int[]{Integer.parseInt(s[0]), Integer.parseInt(s[1])});
        }
    }

    private static void findAnswer() {
        inputs.sort((a, b) -> b[0] - a[0]);
        PriorityQueue<Integer> homework = new PriorityQueue<>(Collections.reverseOrder());
        int maxDay = inputs.get(0)[0];
        int idx = 0;
        int totalScore = 0;

        // 맥스 데이만큼의 과제만 수행할 수 있고 
        // 마감기간이 긴건 언제나 풀 수 있는데
        // 점수가 많은거 위주로 풀어야하니까
        // 마감일을 줄여가면서 마감일과 맞는 과제를 
        // 우선 순위 큐에 점수를 넣으면 높은 점수인게 알아서 앞에 나올거니까
        // 결론적으로 가장 큰 토탈 점수를 구할 수 있음
        for (int day = maxDay; day > 0; day--) {
            while (idx < n && inputs.get(idx)[0] == day) {
                homework.offer(inputs.get(idx)[1]);
                idx++;
            }

            if (!homework.isEmpty()) {
                totalScore += homework.poll();
            }
        }
        System.out.println(totalScore);
    }
}