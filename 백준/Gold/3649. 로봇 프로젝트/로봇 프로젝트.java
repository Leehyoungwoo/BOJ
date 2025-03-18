import java.io.*;
import java.util.Arrays;

public class Main {

    private static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = input.readLine()) != null && !line.isEmpty()) {

            int x = Integer.parseInt(line.trim()); // 안전하게 숫자로 변환
            String nLine = input.readLine();

            if (nLine == null || nLine.isEmpty()) { // n이 없으면 종료
                break;
            }

            int n = Integer.parseInt(nLine.trim()); // 안전한 변환

            if (n == 0) {
                answer.append("danger\n");
                continue;
            }

            int[] legos = new int[n];
            for (int i = 0; i < n; i++) {
                String legoSize = input.readLine();
                if (legoSize == null || legoSize.isEmpty()) {
                    break;
                }
                legos[i] = Integer.parseInt(legoSize.trim());
            }
            Arrays.sort(legos); // 정렬 필수

            int[] bestPair = findBestPair(legos, x * 10000000);
            if (bestPair == null) {
                answer.append("danger\n");
            } else {
                answer.append("yes ").append(bestPair[0]).append(" ").append(bestPair[1]).append("\n");
            }
        }
        System.out.print(answer);
    }

    private static int[] findBestPair(int[] legos, int size) {
        int maxDifference = -1;
        int[] bestPair = null;

        for (int i = 0; i < legos.length; i++) {
            int fixed = legos[i];
            int target = size - fixed;

            // 이분 탐색으로 target을 찾기
            int idx = binarySearch(legos, i + 1, legos.length - 1, target);

            if (idx != -1) { // 찾은 경우
                int diff = Math.abs(legos[idx] - fixed);
                if (diff > maxDifference) { // 최대 차이 갱신
                    maxDifference = diff;
                    bestPair = new int[]{fixed, legos[idx]};
                }
            }
        }
        return bestPair;
    }

    private static int binarySearch(int[] arr, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid; // 정답을 찾으면 반환
            } else if (arr[mid] < target) {
                left = mid + 1; // target이 더 크므로 오른쪽 탐색
            } else {
                right = mid - 1; // target이 더 작으므로 왼쪽 탐색
            }
        }
        return -1; // 찾지 못한 경우
    }
}