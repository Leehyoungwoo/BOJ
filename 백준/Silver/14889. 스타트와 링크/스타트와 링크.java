
import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int[][] scoreBoard;
    private static int minGap = Integer.MAX_VALUE;
    private static List<Integer> startTeam;
    private static List<Integer> likeTeam;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        scoreBoard = new int[n][n];
        startTeam = new ArrayList<>();
        likeTeam = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            for (int j = 0; j < n; j++) {
                scoreBoard[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
    }

    private static void findAnswer() {
        // dfs로 조합 최대 20C10해도 10만 안넘음
        // 조합을 구하고 일단 나머지를 상대 조합에 넣고
        // 점수를 계산, 내 조합 list에 있는지 확인, 상대 조합 list에 있는지 확인해서 각각 sum 구하고 차이 Math.max()해주고 return;
        dfs(0, 0);
        System.out.println(minGap);
    }

    private static void dfs(int idx, int start) {
        if (idx == n / 2) {
            // 반대팀 만들어야지
            for (int i = 0; i < n; i++) {
                if (!startTeam.contains(i)) {
                    likeTeam.add(i);
                }
            }
            int startScore = calculateScore(startTeam);
            int linkScore = calculateScore(likeTeam);
            int gap = Math.abs(linkScore - startScore);
            minGap = Math.min(gap, minGap);
            likeTeam.clear();
            return;
        }

        if (start == n) {
            return;
        }

        startTeam.add(start);
        dfs(idx + 1, start + 1);
        startTeam.remove(startTeam.size() - 1);
        dfs(idx, start + 1);
    }

    private static int calculateScore(List<Integer> team) {
        int sum = 0;
        for (int first : team) {
            for (int second : team) {
                sum += scoreBoard[first][second];
            }
        }

        return sum;
    }
}