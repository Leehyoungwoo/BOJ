import java.util.*;

class Solution {

    public int solution(String[] friends, String[] gifts) {
        int n = friends.length;
        // a -> b vs b -> a 선물하면 다음달 b -> a 선물
        // 둘 중에 주고 받은 기록이 없으면 선물 지수(자신이 준 선물 - 자신이 받은 선물)이 큰 사람이 하나 받음
        // 선물 지수도 같다면 다음달 선물 주고 받지 않음
        List<String> nameList = new ArrayList<>();
        // 이름별 인덱스 계산을 위한 리스트 채우기
        for(int i = 0; i < friends.length; i++) {
            nameList.add(friends[i]);
        }
        
        // 선물 지수 기록하기?
        // 선물의 주고 받음을 기록하기 위한 이차원 배열과 같을 경우를 대비하기 위한 선물 지수 일차원 배열 필요
        int[][] tradeRecord = new int[n][n];
        int[] giftPoint = new int[n];
        for(int i = 0; i < gifts.length; i++) {
            String[] fromTo = gifts[i].split(" ");
            int fromIdx = nameList.indexOf(fromTo[0]);
            int toIdx = nameList.indexOf(fromTo[1]);
            tradeRecord[fromIdx][toIdx]++;
            giftPoint[fromIdx]++;
            giftPoint[toIdx]--;
        }
        // Arrays.stream(giftPoint).forEach(System.out::println);
        // for(int i = 0; i < n; i++) {
        //     for(int j =0; j < n; j++) {
        //         System.out.print(tradeRecord[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        
        // 사람별로 선물을 받는 것을 기록하고 가장 많이 선물을 받는 member를 리턴
        int[] giftCount = new int[n];
        for(int i = 0; i < tradeRecord.length - 1; i++) {
            for(int j = i + 1; j < tradeRecord.length; j++) {
                // if (i == j) {
                //     continue;
                // }
                if (tradeRecord[i][j] > tradeRecord[j][i]) {
                    giftCount[i]++;
                }
                if (tradeRecord[i][j] < tradeRecord[j][i]) {
                    giftCount[j]++;
                }
                if (tradeRecord[i][j] == tradeRecord[j][i]) {
                    if (giftPoint[i] > giftPoint[j]) {
                        giftCount[i]++;
                    }
                    if (giftPoint[i] < giftPoint[j]) {
                        giftCount[j]++;
                    }
                }
            }
        }
        // Arrays.stream(giftCount).forEach(System.out::println);
        
        return Arrays.stream(giftCount).max().getAsInt();
    }
}