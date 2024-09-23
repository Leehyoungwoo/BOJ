import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Solution {

    Map<String, Integer> policy = new HashMap<>();

    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> list = new ArrayList<>();
        // 약관 유효기간 저장
        for (int i = 0; i < terms.length; i++) {
            String[] s = terms[i].split(" ");
            policy.put(s[0], Integer.parseInt(s[1]));
        }

        // 계산해보기
        for (int i = 0; i < privacies.length; i++) {
            String[] s = privacies[i].split(" ");
            String startDay = s[0];
            String type = s[1];
            if (IsDeadline(today, startDay, type) >= 0) {
                list.add(i + 1);
            }
        }

        return list.stream().mapToInt(i -> i).toArray();
    }

    private int IsDeadline(String today, String startDay, String type) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");

        LocalDate todayDate = LocalDate.parse(today, formatter);
        LocalDate startDate = LocalDate.parse(startDay, formatter);

        int validMonth = policy.get(type);

        LocalDate lastDay = convertToLastDay(startDate, validMonth);

        return todayDate.compareTo(lastDay);
    }

    private LocalDate convertToLastDay(LocalDate startDay, int validMonth) {
        return startDay.plusMonths(validMonth);
    }
}