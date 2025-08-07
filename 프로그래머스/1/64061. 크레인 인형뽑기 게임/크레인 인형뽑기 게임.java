import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        // 무브를 주니까
        // 무브 - 1로 이동해서 올라가면서 가장 위에 있는거 catch하고
        // stack에 쌓아
        // stack.peek()와 넣으려는게 같으면 사라지게 하면서 answer+=2인거지
        Stack<Integer> stack = new Stack();
        for (int move : moves) {
            int idx = move - 1;
            int row = 0;
            while (row < board.length ) {
                int doll = board[row][idx];
                if (doll != 0) {
                    board[row][idx] = 0;
                    if (!stack.isEmpty() && stack.peek() == doll) {
                        stack.pop();
                        answer+=2;
                    } else {
                        stack.push(doll);
                    }
                    break;
                }
                row++;
            }
        }
        
        return answer;
    }
}