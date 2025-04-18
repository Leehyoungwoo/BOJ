class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        int n = schedules.length;
        // 배열을 돌면서 직원 별 시간 체크
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < 7; j++) {
                int day = (j + startday);
                if (day > 7) {
                    day-=7;
                }
                // 토요일, 일요일의 출근 시각은 이벤트에 영향을 끼치지 않습니다.
                if (day == 6 || day == 7) {
                    continue;
                }
                // 10분 이내인지 체크해야함
                int time = schedules[i] / 100;
                int minute = schedules[i] % 100;
                // System.out.println(schedules[i] + " " + timelogs[i][j]);
                if (isInTime(time, minute, timelogs[i][j])) {
                    count++;
                }
                // System.out.println(count);
            }
            if (count == 5) {
                answer++;
            }
        }
        return answer;
    }
    
    private boolean isInTime(int time, int minute, int target) {
        int total = time * 60 + minute;
        int targetTotal = (target / 100) * 60 + target % 100;
        int diff = total - targetTotal;
        // System.out.println("기준 : " + (time * 100 + minute));
        // System.out.println("target : " + target);
        // System.out.println("diff : " + diff);
        return diff >= -10;
    }
}