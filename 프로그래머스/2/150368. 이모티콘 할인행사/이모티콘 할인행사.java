import java.util.*;

class Solution {
    // 이모티콘 플러스 서비스 가입자 수 늘리기
    // 가입자 늘리기, 판매액 늘리기 -> 1번이 우선 2번이 그 다음
    // n명에게 m개를 할인해서 판매 10, 20, 30, 40중 하나
    // 사람들은 기준이 있음
    // 일정 비율 할인하는거 모두 구매, 구매 비용 합이 일정 가격을 넘어가면 모두 취소하고 플러스 가입
    // 우리 기준은 가입자 선, 그리고 만약에 같으면 판매액
    // 이모티콘 어떤 할인을 넣을까 순열을 구해야하는데 7개고 할인률 4가지니가 2^14 = 만육천? 충분
    // 중복 순열을 구하고 모든 할인율 적용한 가격표와 통합 금액을 만들어서 가입자 수와 매출 기록
    private final int[] rate = {40, 30, 20, 10};
    private int[][] policy;
    private int[] price;
    // 순열을 담자
    private int[] per;
    private int maxPeople = Integer.MIN_VALUE;
    private int maxSale = Integer.MIN_VALUE;
    private int n;
    
    public int[] solution(int[][] users, int[] emoticons) {
        // 초기화부터 하자
        init(users, emoticons);
        // 일단 이모티콘의 중복 순열부터 구하자
        makePermutation(0);
        
        return new int[] {maxPeople, maxSale};
    }
    
    private void makePermutation(int idx) {
        if (idx == n) {
            // per에 할인율 중복 순열 들어가 있음
            // 사용자는 할인율을 기준으로 선택함
            // 모든 사용자가 모든 이모티콘을 순회하면서 할인욜보다 높아? 그럼 get 그러다가 total이 예산을 넘으면 환불하고 플러스 가입
            int customer = 0;
            int total = 0;
            for (int i = 0; i < policy.length; i++) {
                int priceSum = 0;
                for (int j = 0; j < n; j++) {
                    int saleRate = rate[per[j]];
                    // 할인율이 기준을 충족하면 구매
                    if (saleRate >= policy[i][0]) {
                        int p = (100 - saleRate) * price[j] / 100;
                        priceSum+=p;
                    }
                    
                    // 근데 금액이 예산 이상이면 이제 환불
                }
                if (priceSum >= policy[i][1]) {
                        priceSum = 0;
                        customer++;
                }
                total+=priceSum;
            }
            if (maxPeople < customer) {
                maxPeople = customer;
                maxSale = total;
            } else if (maxPeople == customer) {
                maxSale = Math.max(maxSale, total);
            }
            
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            per[idx] = i;
            makePermutation(idx + 1);
        }
    }
    
    private void init(int[][] users, int[] emoticons) {
        policy = users;
        price = emoticons;
        per = new int[price.length];
        n = price.length;
    }
}
