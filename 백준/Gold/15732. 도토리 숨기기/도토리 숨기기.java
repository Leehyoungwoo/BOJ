import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int k;
    private static int d;
    private static int[][] rules;

    public static void main(String[] args) throws IOException {
        init();
        int answer = findAnswer();

        System.out.println(answer);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());
        d = Integer.parseInt(tokenizer.nextToken());
        rules = new int[k][3];
        for (int i = 0; i < k; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            for (int j = 0; j < 3; j++) {
                rules[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
    }

    private static int findAnswer() {
        // 1부터 N번 까지 n개의 상자가 있고 상자에 도토리를 숨길 것인데 -> A번 상자부터 B번 상자까지 C개 간격으로 도토리를 하나씩 더 넣는 규칙 만듬
        // 이러한 규칙들을 K개를 만들어 도토리를 최대한 안전하게 저장해
        // 예시  100번 상자부터 150번상자까지 10개 간격으로, 110번 상자부터 150번 상자까지 15개 간격으로 넣는다면
        // 100, 110, 120, 125, 130, 140, 150번 상자에 도토리가 있으며 110번 상자와 140번 상자에는 2개의 도토리
        // 상자 하나에 들어갈 수 있는 도토리의 개수는 제한이 없으며 앞의 상자부터 최대한 꽉꽉 채워나간다고 했을 때 마지막 도토리가 들어가 있는 상자의 번호를 출력
        // 범위 k는 1만까지, 도토리는 10억, 상자는 백만
        // 무조건 이분 탐색이네, 문제는 규칙의 범위가 상자 전범위라 규칙을 순회돌면서 상자를 실제로 채워볼수는 없다는 점
        // 일단 규칙 순회는 반드시 필요하지
        // 근데 100 ~ 150까지 10 간격이면 도토리 6개는 쓰는거 아냐? 그럼 대충 생각해보면 (to - from + gap) / gap;의 공식이 성립한다는거
        int left = 1;
        int right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (findCount(mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private static boolean findCount(int mid) {
        long count = 0;
        for (int i = 0; i < k; i++) {
            int from = rules[i][0];
            int to = Math.min(rules[i][1], mid);
            int gap = rules[i][2];
            // 씰링으로 더해주자
            if (to < from) continue;

            count += ((long) to - from + gap) / gap;
        }

        return count < d;
    }
}