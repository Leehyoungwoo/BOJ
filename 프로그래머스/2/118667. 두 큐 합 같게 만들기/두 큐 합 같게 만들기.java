import java.util.*;

class Solution {

    private Queue<Integer> que1 = new LinkedList<>();
    private Queue<Integer> que2 = new LinkedList<>();
    private long que1Sum;
    private long que2Sum;

    public long solution(int[] queue1, int[] queue2) {
        long answer = -1;
        // 전체 sum 구하기
        long total = findAllSum(queue1) + findAllSum(queue2);
        long half = total / 2;

        // 큐에서 빠질때, 넣을 떄마다 sum을 계산하지 않고 변수로 다뤄보기
        que1Sum = findAllSum(queue1);
        que2Sum = findAllSum(queue2);

        // 진짜 큐 채우기
        fillRealQue(que1, queue1);
        fillRealQue(que2, queue2);

        long count = 0;
        while (que1Sum != que2Sum) {
            if (que1Sum > que2Sum) {
                int poll = que1.poll();
                que1Sum -= poll;
                que2.offer(poll);
                que2Sum += poll;
            } else {
                int poll = que2.poll();
                que2Sum -= poll;
                que1.offer(poll);
                que1Sum += poll;
            }
            if (count >= queue1.length + queue2.length + 2) {
                return -1;
            }
            count++;
        }

        return answer == -1 ? count : answer;
    }

    private long findAllSum(int[] queue) {
        return Arrays.stream(queue).sum();
    }

    private void fillRealQue(Queue<Integer> que, int[] queue) {
        for (int i = 0; i < queue.length; i++) {
            que.offer(queue[i]);
        }
    }
}
