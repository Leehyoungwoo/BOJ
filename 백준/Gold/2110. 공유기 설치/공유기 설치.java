import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int c;
    private static int[] house;

    public static void main(String[] args) throws IOException {
        init();
        int answer = findAnswer();

        System.out.println(answer);
    }

    private static int findAnswer() {
        // 구해야하는 것은 가장 인접한 공유기 사이의 거리가 최대가 되게 하는 것
        // 정렬하고 left는 1, right는 최대 - 최소로 해서
        // mid값 잡고 탐색을 하는데 뭐 첫번째 집에 설치하고 dist가 미드보다 크면 설치, 작으면 패스해서 count가 c보다 작거나 같으지를 봐야하는거고
        // 작거나 같으면 left = mid + 1, 크면 dist를 줄여야하니까 right = mid - 1
        Arrays.sort(house);
        int answer = 0;
        int left = 0;
        int right = house[n - 1] - house[0];

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (isPossibleToBuildInC(mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    private static boolean isPossibleToBuildInC(int targetDist) {
        int count = 1;
        // 첫 집에 지었어
        int prev = house[0];
        for (int i = 1; i < n; i++) {
            if (house[i] - prev < targetDist) {

            } else {
                prev = house[i];
                count++;
            }

            if (count >= c) {
                return true;
            }
        }

        return count >= c;
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        c = Integer.parseInt(tokenizer.nextToken());
        house = new int[n];

        for (int i = 0; i < n; i++) {
            house[i] = Integer.parseInt(input.readLine());
        }
    }
}
