import java.util.*;

class Solution {
    public String solution(int[] food) {
        // 칼로리가 작은 순이고
        // 배열 안에 개수가 저장되어 있음
        // 0은 물이니까
        // String빌더 프론트랑 백을 만들어서
        // 2보다 작으면 안쓸거고
        // 2로 나눈다음에 붙이는거고
        // 끝나면 이제 프론트에 0을 붙이고 back을 뒤집어서 프론트에 붙이면 끝
        StringBuilder front = new StringBuilder();
        StringBuilder back = new StringBuilder();
        for (int i = 1; i < food.length; i++) {
            int f = food[i];
            // 2보다 작으면 아예 붙이지 않을거고
            if (f < 2) {
                continue;
            }
            f/=2;
            for (int j = 0; j < f; j++) {
                front.append(i);
                back.append(i);
            }
        }
        front.append(0);
        front.append(back.reverse());
        return front.toString();
    }
}