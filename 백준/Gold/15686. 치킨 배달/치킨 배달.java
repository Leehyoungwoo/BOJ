import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Store {

    int row;
    int col;

    public Store(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

public class Main {

    private static int minDistance = Integer.MAX_VALUE;
    private static int N;
    private static int M;
    private static int[][] map;
    private static int[] per;
    private static ArrayList<Store> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        init();
        dfs(0, per, 0);
        System.out.println(minDistance);

    }

    private static void dfs(int idx, int[] per, int start) {
        if (idx == M) {
            int totalDis = findCityChickenDistance();
            minDistance = Math.min(minDistance, totalDis);
            return;
        }

        if (start == list.size()) {
            return;
        }
        per[idx] = start;
        dfs(idx + 1, per, start + 1);
        per[idx] = -1;
        dfs(idx, per, start + 1);
    }

    private static int findCityChickenDistance() {
        int totalSum = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 1) {
                    continue;
                }
                int min = getEachHouseMinDistance(i, j);
                totalSum += min;
            }
        }

        return totalSum;
    }

    private static int getEachHouseMinDistance(int i, int j) {
        int min = Integer.MAX_VALUE;
        for (int k = 0; k < per.length; k++) {
            int sum = abs(list.get(per[k]).row - i) + abs(list.get(per[k]).col - j);
            min = Math.min(min, sum);
        }
        return min;
    }

    private static int abs(int num) {
        return Math.abs(num);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        map = new int[N][N];
        per = new int[M];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;
                if (num == 2) {
                    list.add(new Store(i, j));
                }
            }
        }

        for (int i = 0; i < per.length; i++) {
            per[i] = -1;
        }
    }
}