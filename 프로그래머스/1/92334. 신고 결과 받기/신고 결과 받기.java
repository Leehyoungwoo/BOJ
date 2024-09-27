import java.util.*;


class Solution {

    private Map<String, Set<String>> countMap = new HashMap<>();

    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        List<String> users = Arrays.asList(id_list);
        // map만들기
        for(String user : users) {
            Set<String> set = new HashSet<>();
            countMap.put(user, set);
        }

        // 신고내용 적립하기, set으로 인해 중복 처리
        for (String r : report) {
            String[] s = r.split(" ");
            String from = s[0];
            String to = s[1];
            countMap.get(to).add(from);
        }

        //map 순회하면서 메일을 받는 사용자 answer에 기록
        for (Map.Entry<String, Set<String>> entry : countMap.entrySet()) {
            String key = entry.getKey();
            Set<String> set = entry.getValue();
            int getReported = set.size();
            if (getReported >= k) {
                for (String reporter : set) {
                    int idx = users.indexOf(reporter);
                    answer[idx]++;
                }
            }
        }

        return answer;
    }
}