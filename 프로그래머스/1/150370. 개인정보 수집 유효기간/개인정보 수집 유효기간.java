import java.util.*;

class Solution {

        private Map<String, Integer> policies = new HashMap<>();

        public int[] solution(String today, String[] terms, String[] privacies) {
            List<Integer> destroy = new ArrayList<>();
            // term을 분해해서 각 정책 별 유효 기간을 저장
            for(int i = 0; i < terms.length; i++) {
                String[] t = terms[i].split(" ");
                policies.put(t[0], Integer.parseInt(t[1]));
            }
            // privacies를 분해하여 각각의 개인 정보 파기 유무를 확인하고 destroy에 넣기
            for (int i = 0; i < privacies.length; i++) {
                String[] p = privacies[i].split(" ");
                String date = p[0];
                String policy = p[1];
                if (isPassed(date, today, policy)) {
                    destroy.add(i + 1);
                }
            }
            // 정렬
            Collections.sort(destroy);

            // int 배열로 바꾸어 return
            return destroy.stream().mapToInt(i -> i).toArray();
        }

        private boolean isPassed(String date, String today, String policy) {
            int plusMonth = policies.get(policy);
            String plusDate = plusMonthToDate(date, plusMonth);

            return plusDate.compareTo(today) < 0;
        }

        private String plusMonthToDate(String date, int plusMonth) {
            String[] d = date.split("\\.");
            int year = Integer.parseInt(d[0]);
            int month = Integer.parseInt(d[1]);
            int day = Integer.parseInt(d[2]);
            // 유효기간 계산
            month+=plusMonth;
            day--;
            if (day == 0) {
                day = 28;
                month--;
            }

            if (month > 12) {
                year += month / 12;
                month = month % 12;            
            }
            
            if (month == 0) {
                year --;
                month = 12;
            }

            return String.format("%04d.%02d.%02d", year, month, day);
        }
    }

