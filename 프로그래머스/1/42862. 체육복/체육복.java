import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        int[] got = new int[n];
        for(int i = 0; i < got.length; i++) {
            got[i] = 1;
        }
        System.out.println(Arrays.toString(got));
        for(int i = 0; i < lost.length; i++) {
            got[lost[i] - 1]--;
        }
        System.out.println(Arrays.toString(got));
        for(int i = 0; i < reserve.length; i++) {
            got[reserve[i] - 1]++;
        }
        System.out.println(Arrays.toString(got));

        for(int i = 0; i < got.length; i++) {
            if(got[i] >= 1) {
                answer++;
                continue;
            }
            if(i == 0 || i == got.length - 1) {
                if (i == 0 && got[i] == 0 && got[i + 1] == 2) {
                answer++;
                got[i + 1]--;
                continue;
            }     
            if (i == got.length - 1 && got[i] == 0 && got[i - 1] == 2) {
                answer++;
                got[i - 1]--;
                continue;
            } 
            } else{
            if (got[i] == 0) {
                if(got[i - 1] == 2) {
                    answer++;
                    got[i - 1] --;
                    continue;
                }
                if(got[i + 1] == 2) {
                    answer++;
                    got[i + 1] --;
                }
            }
        }
        }
        return answer;
    }
}