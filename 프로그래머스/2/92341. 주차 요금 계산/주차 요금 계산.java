import java.util.*;

class Solution {

    int[] globalFees;
    String[] globalRecords;
    Map<String, String> inAndOut;

    public int[] solution(int[] fees, String[] records) {
        int[] answer = {};
        init(fees, records);
        int[] time = findAnswer();
        answer = new int[time.length];
        for (int i = 0; i < time.length; i++) {
            if(time[i] <= fees[0]) {
                answer[i] = fees[1];
            } else {
                double result = (double)(time[i] - fees[0]) / fees[2];
                answer[i] = (int)Math.ceil(result) * fees[3] +fees[1];
            }
        }
        return answer;
    }

    public void init(int[] fees, String[] records) {
        globalFees = fees;
        globalRecords = records;
        inAndOut = new HashMap<>();
    }

    public int[] findAnswer() {
        Map<String, Integer> sumTimePerCar = new HashMap<>();
        List<String> carNumInteger = new ArrayList<>();
        for (String record : globalRecords) {
            String[] str = record.split(" ");
            if (inAndOut.get(str[1]) == null) {
                inAndOut.put(str[1], str[0] + " " + str[2] + " ");
            } else {
                inAndOut.put(str[1], inAndOut.get(str[1]) + str[0] + " " + str[2] + " ");
            }
            if (!carNumInteger.contains(str[1])) {
                carNumInteger.add(str[1]);
            }
        }

        // 오름차순으로 정렬
        Collections.sort(carNumInteger);

        // 나간 기록이 없는 차 23:59분 나감 처리
        for (String inOut : carNumInteger) {
            if (inAndOut.get(inOut).endsWith("IN ")) {
                inAndOut.put(inOut, inAndOut.get(inOut) + "23:59 OUT");
            }
        }

        // 누적 시간 계산해서 맵에 넣기
        for (String num : carNumInteger) {
            String string = inAndOut.get(num);
            String[] re = string.split(" ");
            // in out을 한 쌍으로 시간 계산, in만 있을 경우 out은 23:59분
            // 10분으로 안나눠지면 올림해야함
            for (int i = 0; i < re.length; i += 4) {
                int timeSum = calculateTimeToMin(re[i + 2]) - calculateTimeToMin(re[i]);
                if (sumTimePerCar.get(num) == null) {
                    sumTimePerCar.put(num, timeSum);
                    continue;
                }
                sumTimePerCar.put(num, sumTimePerCar.get(num) + timeSum);
            }
        }

        // answer배열에 순서대로 넣어줘야함
        int idx = 0;
        int[] temp = new int[carNumInteger.size()];
        for (String carNum : carNumInteger) {
            temp[idx] = sumTimePerCar.get(carNum);
            idx++;
        }

        return temp;
    }

    public int calculateTimeToMin(String curTime) {
        String[] str = curTime.split(":");
        int time = Integer.parseInt(str[0]) * 60;
        int min = Integer.parseInt(str[1]);
        return time + min;
    }
}
