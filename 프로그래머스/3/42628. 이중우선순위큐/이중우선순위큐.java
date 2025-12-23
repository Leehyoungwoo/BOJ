import java.util.*;

class Solution {
    // 이중 우선 순위 큐를 만들어야하는데
    // 자료구조를 세개 써야하나?
    // 최소 힙, 최대 힙을 따로 만들고 set에 넣어서 관리하면서
    // 맵으로 개수를 관리를 같이 해줌
    // 삭제 시에는 그 값을 map에서 카운트 제거를 해주고 
    // 최소, 최대 힙에서 뺄 때, 그 값이 맵에 존재하지 않으면 while문으로 poll해준 후에 삭제를 함
    // 연산이 다 끝나고 각각 peek()해주면 될거 같은데
    public int[] solution(String[] operations) {
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        for (int i = 0; i < operations.length; i++) {
            String[] op = operations[i].split(" ");
            int n = Integer.parseInt(op[1]);
            if (op[0].equals("I")) {
                // 삽입 상황
                map.put(n, map.getOrDefault(n, 0) + 1);
                maxHeap.offer(n);
                minHeap.offer(n);
                continue;
            }
            
            while (!minHeap.isEmpty() && map.getOrDefault(minHeap.peek(), 0) == 0) {
                minHeap.poll();
            }
            while (!maxHeap.isEmpty() && map.getOrDefault(maxHeap.peek(), 0) == 0) {
                maxHeap.poll();
            }

            
            if (maxHeap.isEmpty() || minHeap.isEmpty()) {
                System.out.println("PASS " + " " + operations[i]);
                continue;
            }
            
            // 큐에서 최댓값을 삭제하는 경우
            if (n > 0) {
                int num = maxHeap.poll();
                map.put(num, map.get(num) - 1);
                continue;
            }
            // 최솟값을 삭제하는 경우
            else if (n < 0) {
                int num = minHeap.poll();
                map.put(num, map.get(num) - 1);
            }
        }
        
        if (maxHeap.isEmpty() || minHeap.isEmpty()) {
            return new int[] {0, 0};
        }
        
        while (!minHeap.isEmpty() && map.getOrDefault(minHeap.peek(), 0) == 0) {
            minHeap.poll();
        }
        while (!maxHeap.isEmpty() && map.getOrDefault(maxHeap.peek(), 0) == 0) {
            maxHeap.poll();
        }

        
        return new int[] {maxHeap.peek(), minHeap.peek()};
    }
}