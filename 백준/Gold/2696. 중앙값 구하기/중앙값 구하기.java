import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int T;
    private static int m;
    private static StringBuilder answer;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(input.readLine());
        answer = new StringBuilder();

        for (int testcase = 0; testcase < T; testcase++) {
            findAnswer(input);
            answer.append("\n");
        }

        System.out.println(answer);
    }

    private static void findAnswer(BufferedReader input) throws IOException {
        init(input);
        answer.append((m / 2) + 1).append("\n");

        PriorityQueue<Integer> maxQue = new PriorityQueue<>(Collections.reverseOrder()); // 최대 힙 (중간값 왼쪽)
        PriorityQueue<Integer> minQue = new PriorityQueue<>(); // 최소 힙 (중간값 오른쪽)

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        int count = 0; // 10개 출력마다 개행을 넣기 위한 카운트 변수

        for (int i = 0; i < m; i++) {
            if (!tokenizer.hasMoreTokens()) { // 10개 단위로 끊어서 새로운 입력 줄을 받음
                tokenizer = new StringTokenizer(input.readLine());
            }

            int num = Integer.parseInt(tokenizer.nextToken());
            maxQue.offer(num);

            // 최대 힙과 최소 힙의 균형 조정
            if (!minQue.isEmpty() && !maxQue.isEmpty() && minQue.peek() < maxQue.peek()) {
                minQue.offer(maxQue.poll());
            }
            if (minQue.size() > maxQue.size()) {
                maxQue.offer(minQue.poll());
            }
            if (maxQue.size() > minQue.size() + 1) {
                minQue.offer(maxQue.poll());
            }

            // 중간값 출력 (홀수 번째 원소)
            if (i % 2 == 0) {
                answer.append(maxQue.peek()).append(" ");
                count++;

                // 10개 출력했으면 개행 추가
                if (count % 10 == 0) {
                    answer.append("\n");
                }
            }
        }
    }

    private static void init(BufferedReader input) throws IOException {
        m = Integer.parseInt(input.readLine());
    }
}
