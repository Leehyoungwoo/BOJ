import java.util.*;

class Solution {
    public int solution(int number, int limit, int power) {
        // 기사는 1부터 number까지 번호 지정
        // 자신의 기사 번호의 약수 개수에 해당하는 공격력을 가진 무기를 구매하려하는데
        // 협약에 의해 공격력 제한 수치 정함
        // 제한 수치보다 큰 공격력을 가진 무기를 구매하면 협약 기관에서 정한 공격력을 가지는 무기를 구매
        // 무기 만들 때 무기의 공격력 1 당 1kg
        // 무기점에서 무기를 만들려고 함
        // number은 100000
        // 약수를 어떻게 구하지... 
        // sqart까지가 약수인건가? 333정도네 그럼?
        // 우선 number별로 약수를 다 구하고
        // answer에 더하면서 limit보다 낮으면 더하고 limit을 넘으면 power을 더해주는 방식
        int answer = 0;
        for (int i = 1; i <= number; i++) {
            int ad = count(i);
            if (ad <= limit) {
                answer+=ad;
                continue;
            }
            
            answer+=power;
        }
        
        return answer;
    }
    
    private int count(int num) {
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= (int) Math.sqrt(num); i++) {
            // 나누어 떨어저야 약수
            if (num % i == 0) {
                set.add(i);
                set.add(num / i);
            }
        }
        
        return set.size();
    }
}