import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int[][] scorePerInnings;
    private static int[] battingOrder;

    private static int hitter = 1;
    private static int maxScore = 0;

    public static void main(String[] args) throws IOException {
        init();
        findMaxScore(1, new boolean[10]);
        System.out.println(maxScore);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(input.readLine());
        scorePerInnings = new int[N + 1][10];

        for (int i = 1; i <= N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            for (int j = 1; j <= 9; j++) {
                scorePerInnings[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        battingOrder = new int[10];
    }


    private static void findMaxScore(int idx, boolean[] visited) {
        if (idx == 10) {
            hitter = 1;
            int score = findScore();
            maxScore = Math.max(maxScore, score);
            return;
        }

        if (idx == 4) {
            visited[1] = true;
            battingOrder[idx] = 1;
            findMaxScore(idx + 1, visited);
            return;
        }

        for (int i = 2; i <= 9; i++) {
            if (!visited[i]) {
                visited[i] = true;
                battingOrder[idx] = i;
                findMaxScore(idx + 1, visited);
                visited[i] = false;
            }
        }
    }

    private static int findScore() {
        int sum = 0;

        for (int i = 1; i <= N; i++) {
            sum += findEachInning(i);
        }

        return sum;
    }

    private static int findEachInning(int inning) {
        int[] base = new int[3];
        int outCnt = 0;
        int inningScore = 0;

        while (outCnt < 3) {
            if (hitter == 10) {
                hitter -= 9;
            }

            int curHitterExpectValue = scorePerInnings[inning][battingOrder[hitter]];

            if (curHitterExpectValue == 0) {
                outCnt++;
            }

            if (curHitterExpectValue == 1) {
                if (base[2] == 1) {
                    inningScore++;
                    base[2] = 0;
                }

                base[2] = base[1];
                base[1] = base[0];
                base[0] = 1;
            }

            if (curHitterExpectValue == 2) {
                if (base[2] == 1) {
                    inningScore++;
                    base[2] = 0;
                }

                if (base[1] == 1) {
                    inningScore++;
                    base[1] = 0;
                }

                base[2] = base[0];
                base[1] = 1;
                base[0] = 0;
            }

            if (curHitterExpectValue == 3) {
                for (int i = 0; i < base.length; i++) {
                    if (base[i] == 1) {
                        inningScore++;
                        base[i] = 0;
                    }
                }

                base[2] = 1;
            }

            if (curHitterExpectValue == 4) {
                for (int i = 0; i < base.length; i++) {
                    if (base[i] == 1) {
                        inningScore++;
                        base[i] = 0;
                    }
                }

                inningScore++;
            }

            hitter++;
        }

        return inningScore;
    }
}