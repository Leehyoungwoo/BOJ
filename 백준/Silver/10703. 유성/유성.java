import java.io.*;
import java.util.*;

public class Main {

    private static char[][] map;
    private static int r, s;
    private static Star star;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        r = Integer.parseInt(tokenizer.nextToken());
        s = Integer.parseInt(tokenizer.nextToken());
        map = new char[r][s];
        star = new Star();
        for (int i = 0; i < r; i++) {
            String line = input.readLine();
            for (int j = 0; j < s; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'X') {
                    star.point.add(new int[] {i, j});
                }
            }
        }
    }

    private static void findAnswer() {
        int[] gap = new int[s];
        for (int i = 0; i < s; i++) {
            int lastStar = -1;
            int g = 0;
            for (int j = 0; j < r; j++) {
                if (map[j][i] == 'X') {
                    lastStar = j;
                    continue;
                }

                if (map[j][i] == '#' && lastStar != -1) {
                    g = j - lastStar;
                    gap[i] = g - 1;
                    break;
                }
            }
        }

        int minGap = Integer.MAX_VALUE;
        for (int i = 0; i < gap.length; i++) {
            if (gap[i] == 0) {
                continue;
            }
            minGap = Math.min(minGap, gap[i]);
        }

        star.fall(minGap);

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 'X') {
                    map[i][j] = '.';
                }
            }
        }

        for (int[] p : star.point) {
            map[p[0]][p[1]] = 'X';
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < s; j++) {
                answer.append(map[i][j]);
            }

            answer.append("\n");
        }

        System.out.println(answer);
    }

    static class Star {

        private List<int[]> point;

        public Star() {
            point = new ArrayList<>();
        }

        public boolean isPossibleToFall() {
            for (int[] p : point) {
                int nextR = p[0] + 1;
                int nextC = p[1];
                if (map[nextR][nextC] != '.') {
                    return false;
                }
            }

            return true;
        }

        public void fall(int c) {
            for (int[] p : point) {
                p[0]+=c;
            }
        }
    }
}

