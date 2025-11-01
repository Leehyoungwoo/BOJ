import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int m;
    private static int[] playTime;

    public static void main(String[] args) throws IOException {
        init();
        if (n <= m) {
            System.out.println(n);
            return;
        }
        long time = findAnswer();

        // 다 타기 직전의 시간
        long before = count(time - 1);

        // 돌면서 나누어 떨어진다 => 여기서 막 다타고 내려서 들어갈 시간이다.
        for (int i = 0; i < m; i++) {
            if (time % playTime[i] == 0) {
                before++;
                if (before == n) {
                    System.out.println(i + 1);
                    return;
                }
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        playTime = new int[m];
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < m; i++) {
            playTime[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static long findAnswer() {
        // N명의 아이들이 한 줄로 줄을 서서 놀이공원에서 1인승 놀이기구를 기다림
        //  1번부터 M번까지 번호
        // 모든 놀이기구는 각각 운행 시간
        // 운행 시간이 지나면 탑승하고 있던 아이 내림
        // 놀이 기구가 비어 있으면 현재 줄에서 가장 앞에 서 있는 아이가 빈 놀이기구에 탑승
        //  동시에 비어 있으면, 더 작은 번호가 적혀 있는 놀이기구를 먼저 탑승
        // 놀이기구가 모두 비어 있는 상태에서 첫 번째 아이가 놀이기구에 탑승한다고 할 때, 줄의 마지막 아이가 타게 되는 놀이기구의 번호
        long left = 0;
        long right = 1_000_000_000_000_000_000L;
        long answer = 0;
        while(left <= right) {
            long mid = left + (right - left) / 2;
            if (count(mid) >= n) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }

    private static long count(long time) {
        long sum = m;
        for (int i = 0; i < m; i++) {
            sum+=time / playTime[i];
            if (sum >= n) return sum;
        }

        return sum;
    }
}