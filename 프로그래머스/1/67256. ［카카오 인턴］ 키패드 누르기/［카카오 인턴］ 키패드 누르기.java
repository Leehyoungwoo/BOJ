import java.util.*;

class Solution {
    
    private int[] left;
    private int[] right;
    private Map<Integer, int[]> map;
    
    public String solution(int[] numbers, String hand) {
        // 각 번호 별 인덱스를 저장하고
        // left, right 현재 인덱스를 기록해
        // 그리고 numbers를 순회해
        // 1 4 7은 무조건 왼손
        // 3 6 9는 무조건 오른손이래
        // 중간의 2 5 8 0 은 가까운 손으로 하고
        // 같으면 오른손잡이냐 왼손잡이냐로 갈림
        // StringBuilder 하나 선언해서 하면 되겠다.
        StringBuilder answer = new StringBuilder();
        init();
        for (int i = 0; i < numbers.length; i++) {
            int num = numbers[i];
            if (num == 1 || num == 4 || num == 7){
                answer.append('L');
                left[0] = map.get(num)[0];
                left[1] = map.get(num)[1];
                continue;
            }
            
            if (num == 3 || num == 6 || num == 9) {
                answer.append('R');
                right[0] = map.get(num)[0];
                right[1] = map.get(num)[1];
                continue;
            }
            
            // 가운데인 경우 
            int[] point = map.get(num);
            int leftDistance = Math.abs(left[0] - point[0]) + Math.abs(left[1] - point[1]);
            int rightDistance = Math.abs(right[0] - point[0]) + Math.abs(right[1] - point[1]);
            if (leftDistance < rightDistance) {
                left[0] = map.get(num)[0];
                left[1] = map.get(num)[1];
                answer.append('L');
                continue;
            } 
            
            if (leftDistance > rightDistance) {
                right[0] = map.get(num)[0];
                right[1] = map.get(num)[1];
                answer.append('R');
                continue;
            } 
            if (hand.equals("left")) {
                left[0] = point[0];
                left[1] = point[1];
                answer.append('L');
            } else {
                right[0] = point[0];
                right[1] = point[1];
                answer.append('R');
            }

        }
        
        return answer.toString();
    }
    
    private void init() {
        map = new HashMap<>();
        left = new int[] {0, 0};
        right = new int[] {0, 2};
        map.put(0, new int[] {0, 1});
        map.put(1, new int[] {3, 0});
        map.put(2, new int[] {3, 1});
        map.put(3, new int[] {3, 2});
        map.put(4, new int[] {2, 0});
        map.put(5, new int[] {2, 1});
        map.put(6, new int[] {2, 2});
        map.put(7, new int[] {1, 0});
        map.put(8, new int[] {1, 1});
        map.put(9, new int[] {1, 2});
    }
}