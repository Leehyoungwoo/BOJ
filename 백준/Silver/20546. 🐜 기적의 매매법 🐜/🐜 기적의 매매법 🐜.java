import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {


    private static int cash;
    private static int[] flow;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        cash = Integer.parseInt(input.readLine());
        String[] line = input.readLine().split(" ");
        flow = new int[line.length];
        for (int i = 0; i < flow.length; i++) {
            flow[i] = Integer.parseInt(line[i]);
        }
    }

    private static void findAnswer() {
        // 준현
        int junsStock = 0;
        int junsMoney = cash;
        for (int i = 0; i < flow.length; i++) {
            if (junsMoney >= flow[i]) {
                int newStock = junsMoney / flow[i];
                junsMoney -= flow[i] * newStock;
                junsStock += newStock;
            }
        }

        int junsEarn = junsStock * flow[flow.length - 1] + junsMoney;
        // 성민
        int sungsEarn = calculateEarn();

        if (junsEarn > sungsEarn) {
            System.out.println("BNP");
            return;
        }

        if (sungsEarn > junsEarn) {
            System.out.println("TIMING");
            return;
        }

        if (sungsEarn == junsEarn) {
            System.out.println("SAMESAME");
        }
    }

    private static int calculateEarn() {
        //모든 거래는 전량 매수와 전량 매도로 이루어진다. 현재 가지고 있는 현금이 100원이고 주가가 11원이라면 99원어치의 주식을 매수하는 것이다. 단, 현금이 100원 있고 주가가 101원이라면 주식을 살 수 없다. 성민이는 빚을 내서 주식을 하지는 않는다.
        int sungsMoney = cash;
        int sungsStock = 0;
        //3일 연속 가격이 전일 대비 상승하는 주식은 다음날 무조건 가격이 하락한다고 가정한다. 따라서 현재 소유한 주식의 가격이 3일째 상승한다면, 전량 매도한다. 전일과 오늘의 주가가 동일하다면 가격이 상승한 것이 아니다.
        //3일 연속 가격이 전일 대비 하락하는 주식은 다음날 무조건 가격이 상승한다고 가정한다. 따라서 이러한 경향이 나타나면 즉시 주식을 전량 매수한다. 전일과 오늘의 주가가 동일하다면 가격이 하락한 것이 아니다.

        // 주식의 가격을 팔로우하면서 상승, 하강 카운트를 세고, 3일 연속 상승하면 전량 매도, 3일 연속 하강하면 전량 매수해서 마지막 날의 가치 찾아보자
        int idx = 0;
        int prev = 0;
        int downCount = 0;
        int upCount = 0;
        // 주식 가격 흐름을 추적하는데 '연속으로 증가 혹은 감소'했냐가 중요
        while (idx < flow.length) {
            if (prev != 0 && prev < flow[idx]) {
                if (downCount != 0) {
                    upCount = 0;
                    downCount = 0;
                    continue;
                }
                upCount++;

                if (upCount >= 3) {
                    sungsMoney += sungsStock * flow[idx];
                    sungsStock = 0;
                }
            } else if (prev != 0 && prev > flow[idx]) {
                if (upCount != 0) {
                    upCount = 0;
                    downCount = 0;
                    continue;
                }
                downCount++;

                if (downCount >= 3) {
                    int newStock = sungsMoney / flow[idx];
                    sungsMoney = sungsMoney - newStock * flow[idx];
                    sungsStock += newStock;
                }
            } else {
                // 증가, 감소 흐름이 깨지면 다시 카운트 시작해야하니까
                upCount = 0;
                downCount = 0;
            }

            // prev랑 idx증가는 공통
            prev = flow[idx++];
        }

        int sungsEarn = sungsMoney + sungsStock * flow[flow.length - 1];
        return sungsEarn;
    }
}