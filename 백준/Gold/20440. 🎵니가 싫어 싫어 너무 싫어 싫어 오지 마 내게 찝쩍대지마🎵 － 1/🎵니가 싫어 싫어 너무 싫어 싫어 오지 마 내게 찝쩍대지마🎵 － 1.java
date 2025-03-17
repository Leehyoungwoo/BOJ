import java.io.*;
import java.util.*;

public class Main {
    private static int n;
    private static Map<Integer, Integer> map;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(input.readLine());
            int TE = Integer.parseInt(st.nextToken());
            int TX = Integer.parseInt(st.nextToken());

            map.put(TE, map.getOrDefault(TE, 0) + 1);
            map.put(TX, map.getOrDefault(TX, 0) - 1);
        }


    }

    private static void findAnswer() {
        List<Integer> keyList = new ArrayList<>(map.keySet());
        Collections.sort(keyList);

        int maxMoskito = 0;
        int currentMoskito = 0;
        int maxStartTime = 0;
        int maxEndTime = 0;
        boolean isOpened = false;

        for(int key : keyList) {
            int change = map.get(key);

            // 현재 모기 개수 갱신
            currentMoskito += change;

            // 최대 모기 개수 갱신
            if (currentMoskito > maxMoskito) {
                maxMoskito = currentMoskito;
                maxStartTime = key;
                isOpened = true;
            } else if (currentMoskito < maxMoskito && isOpened) {
                maxEndTime = key;
                isOpened = false;
            }
        }

        // 결과 출력
        System.out.println(maxMoskito);
        System.out.println(maxStartTime + " " + maxEndTime);
    }
}