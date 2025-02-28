import java.util.*;

class Solution {
    
    private final int[] percentages = {10, 20, 30, 40};
    private int[] comb;
    private int maxUser = Integer.MIN_VALUE;
    private int maxSales = Integer.MIN_VALUE;

    public int[] solution(int[][] users, int[] emoticons) {
        comb = new int[emoticons.length];
        // 자신의 기준에 따라 일정 비율 이상 할인하는 이모티콘 모두 구매
        makeComb(0, users, emoticons);
        // 구매 비용의 합이 일정 가격 이상이 되면 이모티콘 구매를 취고하고 플러스에 가입
        
        // 목표는 가입자 최대한 늘리기 (최우선) 판매액 최대한 늘리기
        
        // 가입자 수와 매출액 1차원 정수 배열에 담아 return
        return new int[] {maxUser, maxSales};
    }
    
    private void makeComb(int idx, int[][] users, int[] emoticons) {
        if (idx == emoticons.length) {
            // for(int i = 0; i < comb.length; i++) {
            //     System.out.print(comb[i] + " ");
            // }
            // System.out.println();
            int[] salesEmoticons = addSalePriceToEmoticons(emoticons);
            int salseSsum = 0;
            int plusUser = 0;
            for (int i = 0; i < users.length; i++) {
                int sales = 0;
                int userNeedsPercent = users[i][0];
                int userNeedsOverPrice = users[i][1];
                for (int j = 0; j < emoticons.length; j++) {
                    if (userNeedsPercent <= percentages[comb[j]]) {
                        sales+=salesEmoticons[j];
                    }
                    // 만약 sales가 userNeedsOverPrice를 넘으면 반품하고 플러스 가입
                    if (sales >= userNeedsOverPrice) {
                        sales = 0;
                        plusUser++;
                        break;
                    }
                }
                salseSsum+=sales;
            }
            
            if (plusUser > maxUser) {
                maxUser = plusUser;
                maxSales = salseSsum;
            }
            
            if (plusUser == maxUser) {
                if (salseSsum > maxSales) {
                    maxSales = salseSsum;
                }
            }
            return;
        }
        for (int i = 0; i < 4; i++) {
            comb[idx] = i;
            makeComb(idx + 1, users, emoticons);
        }
    }
    
    private int[] addSalePriceToEmoticons(int[] emoticons) {
        int[] salePrice = new int[emoticons.length];
        for(int i = 0; i < salePrice.length; i++) {
            // 세일가 = 원가 * (100 - 세일퍼센트) / 100;
            salePrice[i] = emoticons[i] * (100 - percentages[comb[i]]) / 100;
        }
        
        return salePrice;
    }
}
