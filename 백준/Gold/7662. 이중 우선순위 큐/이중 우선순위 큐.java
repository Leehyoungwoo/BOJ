import java.io.*;
import java.util.*;

public class Main {
    // 이중 우선 큐
    // 연산에 따라 우선 순위가 높은 것 혹은 낮은 것을 제거 가능
    // 삽입 연산 I와 제거 연산 D, 1은 최댓값 제거, -1은 최솟값 제산
    // 큐가 비어있는데 D연산이 나오면 무시

    private static int t;
    private static int k;
    private static String[] lines;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(input.readLine());
        StringBuffer answer = new StringBuffer();
        for (int i = 0; i < t; i++) {
            init(input);
            String output = findAnswer();
            answer.append(output).append("\n");
        }

        System.out.println(answer);
    }

    private static void init(BufferedReader input) throws IOException {
        k = Integer.parseInt(input.readLine());
        lines = new String[k];
        for (int i = 0; i < k; i++) {
            lines[i] = input.readLine();
        }
    }

    private static String findAnswer() {
        // minQue랑 maxQue 선언
        // 양쪽에 둘다 넣음
        // D연산으로 제거되면 set에 넣음
        // D연산으로 제거할 때 set에 포함 여부 확인, 포함되어있으면 한번 더
        // isEmpty()여부 매번 확인
        // 연산이 끝나면 max + " " + min return
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        Map<Integer, Integer> min = new HashMap<>();
        Map<Integer, Integer> max = new HashMap<>();
        for (int i = 0; i < lines.length; i++) {
            String[] s = lines[i].split(" ");
            if (s[0].equals("I")) {
                minHeap.offer(Integer.parseInt(s[1]));
                maxHeap.offer(Integer.parseInt(s[1]));
            } else {
                if (s[1].equals("1")) {
                    // 최댓값 삭제
                    while (!maxHeap.isEmpty() && max.containsKey(maxHeap.peek()) && max.get(maxHeap.peek()) > 0) {
                        int target = maxHeap.poll();
                        max.put(target, max.get(target) - 1);
                    }
                    if (maxHeap.isEmpty()) continue;
                    int target = maxHeap.poll();
                    min.put(target, min.getOrDefault(target, 0) + 1);
                } else {
                    // 최솟값 삭제
                    while (!minHeap.isEmpty() && min.containsKey(minHeap.peek()) && min.get(minHeap.peek()) > 0) {
                        int target = minHeap.poll();
                        min.put(target, min.get(target) - 1);
                    }
                    if (minHeap.isEmpty()) continue;
                    int target = minHeap.poll();
                    max.put(target, max.getOrDefault(target, 0) + 1);
                }
            }
        }
        while (!maxHeap.isEmpty() && max.getOrDefault(maxHeap.peek(), 0) > 0) {
            int target = maxHeap.poll();
            max.put(target, max.get(target) - 1);
        }
        while (!minHeap.isEmpty() && min.getOrDefault(minHeap.peek(), 0) > 0) {
            int target = minHeap.poll();
            min.put(target, min.get(target) - 1);
        }

        if (minHeap.isEmpty() || maxHeap.isEmpty()) {
            return "EMPTY";
        }

        return maxHeap.peek() + " " + minHeap.peek();
    }
}