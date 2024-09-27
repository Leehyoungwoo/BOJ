import java.util.*;
import java.time.*;

class Solution {

    Set<String> cars = new HashSet<>();
    Map<String, String> carStatus = new HashMap<>();
    Map<String, Long> carPerTime = new HashMap<>();
    private final String[] outTime = {"23", "59"};

    public int[] solution(int[] fees, String[] records) {
        int[] answer = {};

        for (int i = 0; i < records.length; i++) {
            String[] s = records[i].split(" ");
            String time = s[0];
            String car = s[1];
            String type = s[2];
            // 마지막에 차넘버로 정렬하려고 만든 set임
            cars.add(car);
            // type이 in이면 status에 시간 보관해주고 out이면 기존 시간 빼와서 아웃 시간이랑 비교해서 요금 산출
            if (type.equals("IN")) {
                carStatus.put(car, time);
                continue;
            }

            // 요금 처리
            if (type.equals("OUT")) {
                String in = carStatus.get(car);
                String[] inTime = in.split(":");
                LocalTime startTime = LocalTime.of(Integer.parseInt(inTime[0]), Integer.parseInt(inTime[1]));
                String[] outTime = time.split(":");
                LocalTime endTime = LocalTime.of(Integer.parseInt(outTime[0]), Integer.parseInt(outTime[1]));
                long parkingTime = Duration.between(startTime, endTime).toMinutes();
                carPerTime.put(car, carPerTime.getOrDefault(car, 0L) + parkingTime);
                carStatus.put(car, null);
            }
        }
        // in만 있는 차량 요금 처리
        for (Map.Entry<String, String> entry : carStatus.entrySet()) {
            if (!Objects.isNull(entry.getValue())) {
                String car = entry.getKey();
                String in = carStatus.get(car);
                String[] inTime = in.split(":");
                LocalTime startTime = LocalTime.of(Integer.parseInt(inTime[0]), Integer.parseInt(inTime[1]));
                LocalTime endTime = LocalTime.of(Integer.parseInt(outTime[0]), Integer.parseInt(outTime[1]));
                long parkingTime = Duration.between(startTime, endTime).toMinutes();
                carPerTime.put(car, carPerTime.getOrDefault(car, 0L) + parkingTime);
                carStatus.put(car, null);
            }
        }

        List<String> carList = new ArrayList<>(cars);
        Collections.sort(carList);
        answer = new int[carList.size()];
        for (int i = 0; i < carList.size(); i++) {
            String car = carList.get(i);
            long time = carPerTime.get(car);
            answer[i] = this.caculateFee(time, fees);
        }


        return answer;
    }

    private int caculateFee(long parkingTime, int[] fees) {
        int basicTime = fees[0];
        int basicPrice = fees[1];
        int addTime = fees[2];
        int addPrice = fees[3];

        if (parkingTime <= basicTime) {
            return basicPrice;
        }

        int extraPrice = 0;
        if (parkingTime > basicTime) {
            int extraTime = (int) (parkingTime - basicTime);
            if (extraTime % addTime == 0) {
                extraPrice = extraTime / addTime * addPrice;
            } else {
                extraPrice = (extraTime / addTime + 1) * addPrice;
            }

        }

        return basicPrice + extraPrice;
    }
}
