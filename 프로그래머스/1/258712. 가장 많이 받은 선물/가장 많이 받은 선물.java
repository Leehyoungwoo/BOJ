import java.util.*;

class Solution {

    private int l;
    private Map<String, Integer> map = new HashMap<>();

    public int solution(String[] friends, String[] gifts) {
        l = friends.length;
        findIdx(friends);
        int[][] lastMonthCount = new int[l][l];
        int[] giftPoint = new int[l];
        for (String record : gifts) {
            String[] fromTo = record.split(" ");
            int from = map.get(fromTo[0]);
            int to = map.get(fromTo[1]);
            lastMonthCount[from][to]++;
            giftPoint[from]++;
            giftPoint[to]--;
        }

        int[] thisMonthCount = new int[l];
        for (int i = 0; i < l; i++) {
            for (int j = i + 1; j < l; j++) {
                if (i == j) {
                    continue;
                }
                if (lastMonthCount[i][j] > lastMonthCount[j][i]) {
                    thisMonthCount[i]++;
                } else if (lastMonthCount[i][j] < lastMonthCount[j][i]) {
                    thisMonthCount[j]++;
                } else {
                    if (giftPoint[i] > giftPoint[j]) {
                        thisMonthCount[i]++;
                    } else if (giftPoint[i] < giftPoint[j]) {
                        thisMonthCount[j]++;
                    }
                }
            }
        }
        int max = Arrays.stream(thisMonthCount).max().orElse(0);

        return max;
    }

    private void findIdx(String[] friends) {
        for (int i = 0; i < friends.length; i++) {
            String key = friends[i];
            map.put(key, i);
        }
    }
}