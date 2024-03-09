import java.util.*;

class Solution {

    int[] endTimePerType;
    String[] endTimePerPri;

    public int[] solution(String today, String[] terms, String[] privacies) {
        int[] answer = {};
        endTimePerType = new int[26];
        // 타입 별 만료기간
        fillEndTimePerType(terms);
        endTimePerPri = new String[privacies.length];
        // 개인정보별 만료 기간
        fillEndTimePerPri(privacies);
        answer = findEndPrivacies(today, endTimePerPri, answer);
        return answer;
    }

    private void fillEndTimePerType(String[] terms) {
        for (int i = 0; i < terms.length; i++) {
            String[] s = terms[i].split(" ");
            int idx = s[0].charAt(0) - 'A';
            endTimePerType[idx] = Integer.parseInt(s[1]);
        }
    }

    private void fillEndTimePerPri(String[] privacies) {
        for (int i = 0; i < privacies.length; i++) {
            String[] s = privacies[i].split(" ");
            String date = s[0];
            String type = s[1];
            String[] days = date.split("\\.");
            int year = Integer.parseInt(days[0]);
            int month = Integer.parseInt(days[1]);
            int day = Integer.parseInt(days[2]);
            month += endTimePerType[type.charAt(0) - 'A'];
            // 달에 관한 이슈
            if (1 <= month && month <= 12) {
                day--;
                if (day == 0) {
                    day = 28;
                    month--;
                    if (month == 0) {
                        month = 12;
                        year--;
                    }
                }
            } else if (month > 12) {
                if (month % 12 == 0) {
                    year += (month / 12) - 1;
                    month = 12;
                    day--;
                } else {
                    year += month / 12;
                    month %= 12;
                    day--;
                }

                if (day == 0) {
                    day = 28;
                    month--;
                    if (month == 0) {
                        month = 12;
                        year--;
                    }
                }
            }
            // 타입별 만료일 기록
            endTimePerPri[i] = year + "." + month + "." + day;
        }
    }

    private int[] findEndPrivacies(String today, String[] endTimePerPri, int[] answer) {
        int[] temp = new int[100];
        int idx = 0;
        for (int i = 0; i < endTimePerPri.length; i++) {
            if (!validateExpired(endTimePerPri[i], today)) {
                temp[idx] = i;
                idx++;
            }
        }
        answer = new int[idx];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = temp[i] + 1;
        }

        return answer;
    }

    private boolean validateExpired(String privacy, String today) {
        String[] str = privacy.split(" ");
        String[] s = str[0].split("\\.");
        int endYear = Integer.parseInt(s[0]);
        int endMonth = Integer.parseInt(s[1]);
        int endDay = Integer.parseInt(s[2]);
        String[] cur = today.split("\\.");
        int curYear = Integer.parseInt(cur[0]);
        int curMonth = Integer.parseInt(cur[1]);
        int curDay = Integer.parseInt(cur[2]);
        if (endYear > curYear) {
            return true;
        } else if (endYear == curYear) {
            if (endMonth > curMonth) {
                return true;
            } else if (endMonth == curMonth) {
                if (endDay >= curDay) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        return false;
    }
}