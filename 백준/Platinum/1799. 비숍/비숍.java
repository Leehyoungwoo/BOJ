import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int[][] map;

    private static List<int[]> indexBlack;
    private static List<int[]> indexWhite;

    // 대각선 점유(충돌) 상태
    private static boolean[] usedDiagSum;   // r + c
    private static boolean[] usedDiagDiff;  // r - c + (n-1)

    private static int bestBlack, bestWhite;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        map = new int[n][n];
        indexBlack = new ArrayList<>();
        indexWhite = new ArrayList<>();

        for (int r = 0; r < n; r++) {
            StringTokenizer st = new StringTokenizer(input.readLine());
            for (int c = 0; c < n; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == 1) {
                    if (((r + c) % 2) == 0) {
                        indexBlack.add(new int[]{r, c});
                    } else {
                        indexWhite.add(new int[]{r, c});
                    }
                }
            }
        }

        usedDiagSum = new boolean[2 * n];
        usedDiagDiff = new boolean[2 * n];
    }

    private static void findAnswer() {
        bestBlack = 0;
        bestWhite = 0;

        // 흑부터 처리
        dfsColor(indexBlack, 0, 0, true);

        // 대각선 상태 초기화 후 백 처리
        Arrays.fill(usedDiagSum, false);
        Arrays.fill(usedDiagDiff, false);
        dfsColor(indexWhite, 0, 0, false);

        System.out.println(bestBlack + bestWhite);
    }

    private static void dfsColor(List<int[]> index, int idx, int count, boolean isBlack) {
        // 남은 칸 전부 놓아도 현재 best 못 넘으면 컷, 가지치기
        int remain = index.size() - idx;
        int currentBest = isBlack ? bestBlack : bestWhite;
        if (count + remain <= currentBest) return;

        if (idx == index.size()) {
            if (isBlack) {
                bestBlack = Math.max(bestBlack, count);
            } else {
                bestWhite = Math.max(bestWhite, count);
            }
            return;
        }

        int r = index.get(idx)[0];
        int c = index.get(idx)[1];
        int dSum = r + c;
        int dDiff = r - c + (n - 1);

        if (!usedDiagSum[dSum] && !usedDiagDiff[dDiff]) {
            usedDiagSum[dSum] = usedDiagDiff[dDiff] = true;
            dfsColor(index, idx + 1, count + 1, isBlack);
            // 백트래킹
            usedDiagSum[dSum] = usedDiagDiff[dDiff] = false;
        }

        // 안 놓기
        dfsColor(index, idx + 1, count, isBlack);
    }
}