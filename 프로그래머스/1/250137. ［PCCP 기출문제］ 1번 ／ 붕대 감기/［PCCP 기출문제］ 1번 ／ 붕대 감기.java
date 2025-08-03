import java.util.*;

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = 0;
        // 붕대 감기
        // t초동안 감으면서 1초에 x만큼 체력 회복
        // x초 연속으로 감는데 성공하면 y만큼 추가 회복
        // 캐릭터는 최대 체력이 존재해
        // 붕대 감는 중에 공격 당하면 기술 취소되고 순간에 체력 회복할 수 없음
        // 기술이 취소당하거나 끝나면 그 즉시 붕대감기 다시 사용하며 연속 성공 시간 0 초기화
        // 공격 받으면 정해진 피해량만큼 체력이 줄어듬 0이하가 되면 캐릭터 다이
        // 캐릭터 다이되면 -1 return
        // bandage는 시전 시간, 초당 회복, 추가 회복
        // 어택은 공격 시간, 피해량
        // 최대체력일때는 붕대감기 못함
        // 맵에 공격 시간과 데미지를 담을까?
        boolean isAlive = true;
        Map<Integer, Integer> attackTiming = new HashMap<>();
        int lastTime = 0;
        for (int i = 0; i < attacks.length; i++) {
            int time = attacks[i][0];
            int damage = attacks[i][1];
            lastTime = Math.max(lastTime, time);
            attackTiming.put(time, damage);
        }
        // 시간을 순회돌면서 각 초마다 체력을 컨트롤
        // curHealth가 최대체력보다 떨어지면 힐링 시작, 최대 체력을 넘을 수 없음
        // 공격받으면 힐링 중단 후 다시 시작
        // 연속 회복이 시전시간이 되면 추가 체력 획득
        int curHealth = health;
        int count = 0;
        // bandage를 꺼내자
        int healTime = bandage[0];
        int healPerTime = bandage[1];
        int wholeHeal = bandage[2];
        for (int i = 1; i <= lastTime; i++) {
            // 공격이 있는지 확인
            if (attackTiming.containsKey(i)) {
                int damage = attackTiming.get(i);
                curHealth-=damage;
                count = 0;
                if (curHealth <= 0) {
                    isAlive = false;
                    break;
                }
            } else {
                // 공격이 없으면 
                // 최대 체력이면 continue;
                if (curHealth == health) {
                    count++;
                    continue;
                } else {
                    // count가 시전시간인 경우와 아닌 경우
                    count++;
                    if (count < healTime) {
                        curHealth+=healPerTime;
                        if (curHealth > health) {
                            curHealth = health;
                        }
                    } else {
                        curHealth+=(healPerTime + wholeHeal);
                        count = 0;
                        if (curHealth > health) {
                            curHealth = health;
                        }
                    }
                }
            }
        }
        
        return isAlive == true ? curHealth : -1;
    }
}