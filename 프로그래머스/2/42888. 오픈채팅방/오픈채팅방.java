import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> nicknamePerPk = new HashMap<>();
        Queue<String> message = new LinkedList<>();
        Queue<String> idSeq = new LinkedList<>();
        for(int i = 0; i < record.length; i++) {
            String[] str = record[i].split(" ");
            if (record[i].startsWith("Enter")) {
                String act = str[0];
                String userPk = str[1];
                String nickname = str[2];
                message.offer("님이 들어왔습니다.");
                idSeq.offer(userPk);
                nicknamePerPk.put(userPk, nickname);
                continue;
            }
            
            if (record[i].startsWith("Leave")) {
                String act = str[0];
                String userPk = str[1];
                message.offer("님이 나갔습니다.");
                idSeq.offer(userPk);
                continue;
            }
            
            if (record[i].startsWith("Change")) {
                String userPk = str[1];
                String nickname = str[2];
                nicknamePerPk.put(userPk, nickname);
            }
        }
        int size = idSeq.size();
        String[] answer = new String[size];
        for (int i = 0; i < answer.length; i++) {
            String pk = idSeq.poll();
            String mes = message.poll();
            answer[i] = nicknamePerPk.get(pk) + mes;
        }
        
        return answer;
    }
}