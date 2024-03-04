import java.util.Arrays;

class Solution {
    
    private static int[][] giftRate;
    private static int[] result;

    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        giftRate = new int[friends.length][friends.length];
        result = new int[friends.length];
        fillGiftRate(gifts, friends);
        givePresent();
        Arrays.sort(result);
        answer = result[friends.length - 1];
        return answer;
    }
    
    private void fillGiftRate(String[] gifts, String[] friends) {
        for (String s : gifts) {
            int from = 0;
            int to = 0;
            String[] fromTo = s.split(" ");
            for (int i = 0; i < result.length; i++) {
                if (fromTo[0].equals(friends[i])) {
                    from = i;
                    continue;
                }
                if (fromTo[1].equals(friends[i])) {
                    to = i;
                }
            }
            giftRate[from][to]++;
        }
    }

    private void givePresent() {
        for (int i = 0; i < result.length; i++) {
            for (int j = i + 1; j < result.length; j++) {
                if(i == j) continue;
                if (giftRate[i][j] > giftRate[j][i]) {
                    result[i]++;
                    continue;
                }

                if (giftRate[i][j] < giftRate[j][i]) {
                    result[j]++;
                }

                if (giftRate[i][j] == giftRate[j][i]) {
                    int iSum = sum(i);
                    int jSum = sum(j);
                    if (iSum > jSum) {
                        result[i]++;
                    }
                    if (iSum < jSum) {
                        result[j]++;
                    }
                }
            }
        }
    }

    private int sum(int n) {
        int sum = 0;
        for (int i = 0; i < result.length; i++) {
            sum += giftRate[n][i];
        }
        for(int i = 0; i < result.length; i++) {
            sum-= giftRate[i][n];
        }
        return sum;
    }

}