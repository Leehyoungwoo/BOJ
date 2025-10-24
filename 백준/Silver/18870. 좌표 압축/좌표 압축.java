import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int[] X;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        X = new int[n];
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            X[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void findAnswer() {
        // Xi > Xj를 만족하는 서로 다른 좌표 Xj의 개수
        // 현재 내 값보다 작은 것들 -> target을 만족하는 첫번쨰 인덱스 => lowerBound
        // 우선 원 배열이 정렬되어야하니까 복사 배열 필요 -> 중복 제거해야되네
        List<Integer> values = new ArrayList<>();
        Set<Integer> contains = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (!contains.contains(X[i])) {
                contains.add(X[i]);
                values.add(X[i]);
            }
        }
        StringBuilder answer = new StringBuilder();
        Collections.sort(values);
        for (int i = 0; i < n; i++) {
            int target = X[i];
            int count = lowerBoundBinarySearch(target, values);
            answer.append(count).append(" ");
        }

        System.out.println(answer);
    }

    private static int lowerBoundBinarySearch(int target, List<Integer> values) {
        int left = 0;
        int right = values.size();
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (values.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}