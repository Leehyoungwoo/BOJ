import java.util.*;

class Solution {

    private int[] discountRate = {10, 20, 30, 40};
    private int maxUser = Integer.MIN_VALUE;
    private int maxSales = Integer.MIN_VALUE;
    private int[] comb;

    public int[] solution(int[][] users, int[] emoticons) {
        comb = new int[emoticons.length];
        findPer(0, users, emoticons);
        return new int[]{maxUser, maxSales};
    }

    private void findPer(int idx, int[][] users, int[] emoticons) {
        if (idx == comb.length) {
            int[] usersAndSales = findUser(users, emoticons);
            if (usersAndSales[0] > maxUser) {
                maxUser = usersAndSales[0];
                maxSales = usersAndSales[1];
            }
            if (usersAndSales[0] == maxUser) {
                maxSales = Math.max(usersAndSales[1], maxSales);
            }
            return;
        }

        for (int i = 0; i < discountRate.length; i++) {
            comb[idx] = i;
            findPer(idx + 1, users, emoticons);
        }
    }

    private int[] findUser(int[][] users, int[] emoticons) {
        int totalPeople = 0;
        int totalSales = 0;
        for (int i = 0; i < users.length; i++) {
            int buy = 0;
            int rate = users[i][0];
            int range = users[i][1];
            for (int j = 0; j < emoticons.length; j++) {
                if (discountRate[comb[j]] >= rate) {
                    buy += (100 - discountRate[comb[j]])  * emoticons[j] / 100;
                }
                if (buy >= range) {
                    buy = 0;
                    totalPeople++;
                    break;
                }
            }
            totalSales += buy;
        }

        return new int[] {totalPeople, totalSales};
    }
}
