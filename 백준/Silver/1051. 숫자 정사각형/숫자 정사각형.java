import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int n, m;
    private static int[][] map;
    private static int answer;

    public static void main(String[] args) throws IOException {
        init();
        findLargestSize();
        System.out.println(answer);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String s = input.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }
    }

    private static void findLargestSize() {
        int maxSize = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int size = 1; i + size < n && j + size < m; size++) {
                    if (map[i][j] == map[i][j + size] &&
                            map[i][j] == map[i + size][j] &&
                            map[i][j] == map[i + size][j + size]) {

                        int squareSize = (size + 1) * (size + 1);
                        maxSize = Math.max(maxSize, squareSize);
                    }
                }
            }
        }

        answer = maxSize;
    }

}