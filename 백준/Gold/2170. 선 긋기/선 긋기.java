import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int[][] seg;

    public static void main(String[] args) throws IOException {
        init();
        long answer = findAnswer();

        System.out.println(answer);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(input.readLine());
        seg = new int[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());
            seg[i][0] = x;
            seg[i][1] = y;
        }

        Arrays.sort(seg, Comparator.comparingInt(a -> a[0]));
    }

    private static long findAnswer() {
        // 선을 그을건데 그 선이 그어지는 총 길이를 구하는거고
        // 선의 길이는 end - start로 간주하는 모양
        // 그렇다면 우선 순위 큐로 시작점 정렬을 해놓고 구간이 끝날 떄 길이 sum에 더해주는 방식을 사용하는게 나아보임
        // 범위는 -1,000,000,000 <= p <= 1,000,000,000
        // 병합하면서 갱신해나가야 함
        int curStart = seg[0][0];
        int curEnd = seg[0][1];
        long sum = 0;

        for (int i = 1; i < seg.length; i++) {
            int start = seg[i][0];
            int end = seg[i][1];

            if (curEnd >= start) {
                curEnd = Math.max(curEnd, end);
            } else {
                sum += curEnd - curStart;
                curStart = start;
                curEnd = end;
            }
        }
        // 마지막 보정이 필요한거 같은데
        sum += (curEnd - curStart);

        return sum;
    }
}