
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static char[][] map;
    private static int R;
    private static int C;
    private static int[] S;
    private static List<int[]> waterList = new ArrayList<>();
    private static char rock = 'X';

    public static void main(String[] args) throws IOException {
        init();
        printAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        R = Integer.parseInt(tokenizer.nextToken());
        C = Integer.parseInt(tokenizer.nextToken());

        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String str = input.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'S') {
                    S = new int[]{i, j};
                }

                if (map[i][j] == '*') {
                   waterList.add(new int[]{i, j});
                }
            }
        }
    }

    private static void printAnswer() {
        int time = 0;
        Queue<int[]> water = new LinkedList<>();
        Queue<int[]> dog = new LinkedList<>();
        boolean[][] visited1 = new boolean[R][C];
        boolean[][] visited2 = new boolean[R][C];

        for (int[] w : waterList) {
            water.add(w);
            visited1[w[0]][w[1]] = true;
        }

        dog.add(S);
        visited2[S[0]][S[1]] = true;

        while (!dog.isEmpty()) {
            time++;

            // 물을 한 스텝씩 이동시킴
            int size1 = water.size();
            for (int i = 0; i < size1; i++) {
                int[] wt = water.poll();
                int curWRow = wt[0];
                int curWCol = wt[1];

                for (int[] dir : direction) {
                    int nextWRow = curWRow + dir[0];
                    int nextWCol = curWCol + dir[1];
                    if (isInRange(nextWRow, nextWCol) && !visited1[nextWRow][nextWCol]
                            && map[nextWRow][nextWCol] != rock && map[nextWRow][nextWCol] != 'D') {
                        map[nextWRow][nextWCol] = '*';
                        water.add(new int[]{nextWRow, nextWCol});
                        visited1[nextWRow][nextWCol] = true;
                    }
                }
            }

            // 고슴도치를 한 스텝씩 이동시킴
            int size2 = dog.size();
            for (int i = 0; i < size2; i++) {
                int[] hog = dog.poll();
                int curSRow = hog[0];
                int curSCol = hog[1];

                for (int[] dir : direction) {
                    int nextSRow = curSRow + dir[0];
                    int nextSCol = curSCol + dir[1];
                    if (isInRange(nextSRow, nextSCol) && !visited2[nextSRow][nextSCol]
                            && map[nextSRow][nextSCol] != rock && map[nextSRow][nextSCol] != '*') {
                        if (map[nextSRow][nextSCol] == 'D') {
                            System.out.println(time);
                            return;
                        }
                        map[nextSRow][nextSCol] = 'S';
                        dog.add(new int[]{nextSRow, nextSCol});
                        visited2[nextSRow][nextSCol] = true;
                    }
                }
            }
        }

        System.out.println("KAKTUS");
    }

    private static boolean isInRange(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }
}
