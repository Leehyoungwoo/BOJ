import java.util.*;

class Solution {

    long target;
    Queue<Long> que1 = new LinkedList<>();
    Queue<Long> que2 = new LinkedList<>();
    long globalCount;

    public long solution(int[] queue1, int[] queue2) {
        long answer = -1;
        init(queue1, queue2);
        long s1 = findAllSum(que1);
        long s2 = findAllSum(que2);

        if ((s1 + s2) % 2 != 0) {
            return answer;
        }

        int count = 0;
        int totalMoves = que1.size() + que2.size() * 2;
        while (count < totalMoves) {
            if (s1 == target) {
                return count;
            }

            if (s1 < target && !que2.isEmpty()) {
                long n = que2.poll();
                que1.offer(n);
                s1 += n;
                s2 -= n;
            } else if (s1 > target && !que1.isEmpty()) {
                long n = que1.poll();
                que2.offer(n);
                s1 -= n;
                s2 += n;
            }
            count++;

            // 한계 상황을 넘어서면 종료
            if(count > 600000) {
                break;
            }
        }

        return -1; // 조건을 만족하는 경우를 찾지 못했을 때
    }

    public void init(int[] queue1, int[] queue2) {
        long sum = 0;
        for (int i = 0; i < queue1.length; i++) {
            que1.offer((long) queue1[i]);
            sum += queue1[i];
        }

        for (int i = 0; i < queue2.length; i++) {
            que2.offer((long) queue2[i]);
            sum += queue2[i];
        }

        target = sum / 2;
    }

    public long findAllSum(Queue<Long> que) {
        long sum = 0;
        for (long n : que) {
            sum += n;
        }
        return sum;
    }
}
