class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
        // 초를 return하는 함수 만들기
        // prev 함수
        // next 함수
        int videoTime = makeStringToSecond(video_len);
        int start = makeStringToSecond(pos);
        int op_s = makeStringToSecond(op_start);
        int op_e = makeStringToSecond(op_end);
        for (int i = 0;  i < commands.length; i++) {
            String c = commands[i];
            if (start >= op_s && start <= op_e) {
                start = op_e;
            }
            if (c.equals("next")) {
                start = next(start, videoTime);
            }
            
            if (c.equals("prev")) {
                start = prev(start);
            }
            if (start >= op_s && start <= op_e) {
                start = op_e;
            }
            System.out.println("시간 : "+String.format("%02d:%02d", start / 60, start % 60));
        }
        
        return String.format("%02d:%02d", start / 60, start % 60);
    }
    
    private int next(int time, int total) {
        if (time + 10 >= total) {
            return total;
        }
        
        return time + 10;
    }
    
    private int prev(int time) {
        if (time - 10 <= 0) {
            return 0;
        }
        
        return time - 10;
    }
    
    private int makeStringToSecond(String time) {
        String[] times = time.split(":");
        return Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]);
    }
}