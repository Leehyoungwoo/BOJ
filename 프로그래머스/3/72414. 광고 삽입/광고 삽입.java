import java.util.*;

class Solution {
    
    public String solution(String play_time, String adv_time, String[] logs) {
        int playTime = timeToSeconds(play_time);
        int advTime = timeToSeconds(adv_time);
        
        // 각 초마다 시청자 수를 기록할 배열
        long[] totalPlayTimes = new long[playTime + 1];
        
        // 각 재생 구간을 초 단위로 변환하여 시청자 수를 누적
        for (String log : logs) {
            String[] times = log.split("-");
            int start = timeToSeconds(times[0]);
            int end = timeToSeconds(times[1]);
            totalPlayTimes[start]++;
            if (end <= playTime) totalPlayTimes[end]--;
        }
        
        // 누적 재생 시간을 구함
        for (int i = 1; i <= playTime; i++) {
            totalPlayTimes[i] += totalPlayTimes[i - 1];
        }
        
        // 누적 재생 시간을 다시 누적합으로 구함
        for (int i = 1; i <= playTime; i++) {
            totalPlayTimes[i] += totalPlayTimes[i - 1];
        }
        
        // 광고가 삽입될 최적의 시작 시각을 찾음
        long maxPlayTime = totalPlayTimes[advTime - 1];
        int startTime = 0;
        
        for (int i = advTime; i <= playTime; i++) {
            long currentPlayTime = totalPlayTimes[i] - totalPlayTimes[i - advTime];
            if (currentPlayTime > maxPlayTime) {
                maxPlayTime = currentPlayTime;
                startTime = i - advTime + 1;
            }
        }
        
        return secondsToTime(startTime);
    }

        // 시간을 초 단위로 변환하는 함수
    public int timeToSeconds(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        int seconds = Integer.parseInt(parts[2]);
        return hours * 3600 + minutes * 60 + seconds;
    }
    
    // 초를 시간 형식으로 변환하는 함수
    public String secondsToTime(int seconds) {
        int hours = seconds / 3600;
        seconds %= 3600;
        int minutes = seconds / 60;
        seconds %= 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
