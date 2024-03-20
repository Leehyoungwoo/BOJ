import java.util.*;

class Solution {
    public int[] solution(int n, int[] stages) {
        int[] answer = new int[n];
        int[] totalUser = new int[n];
        int[] fail = new int[n];

        for (int i = 0; i < stages.length; i++) {
            for (int j = 0; j < stages[i]; j++) {
                if (j == n) {
                    continue;
                }
                totalUser[j]++;
            }
        }
        for (int i = 0; i < stages.length; i++) {
            if (stages[i] == n + 1) {
                continue;
            }
            fail[stages[i] - 1]++;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < answer.length; i++) {
            pq.add(new Node((double) fail[i] / totalUser[i], i + 1));
        }

        for (int i = 0; i < answer.length; i++) {
            answer[i] = pq.poll().round;
        }

        return answer;
    }
}

class Node implements Comparable<Node> {
    double failRate;
    int round;

    public Node(double failRate, int round) {
        this.failRate = failRate;
        this.round = round;
    }

    @Override
    public int compareTo(Node o) {
        if (this.failRate > o.failRate) {
            return -1;
        } else if (this.failRate < o.failRate) {
            return 1;
        } else {
            // failRate가 같은 경우 round를 비교하여 작은 순서로 정렬
            return this.round - o.round;
        }
    }
}