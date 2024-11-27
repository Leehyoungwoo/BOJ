import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int n;
    private static char[][] relations;
    private static int answer;

    public static void main(String[] args) throws IOException {
        init();
        findMaxTwoFriends();
        System.out.println(answer);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        relations = new char[n][n];
        for (int i = 0; i < n; i++) {
            relations[i] = input.readLine().toCharArray();
        }
    }

    private static void findMaxTwoFriends() {
        for (int i = 0; i < n; i++) {
            boolean[] visited = new boolean[n];
            int friendCount = 0;

            for (int j = 0; j < n; j++) {
                if (i == j) continue;

                // 직접 친구거나 2-친구라면 방문 처리
                if (relations[i][j] == 'Y' || isTwoFriend(i, j)) {
                    if (!visited[j]) {
                        visited[j] = true;
                        friendCount++;
                    }
                }
            }

            answer = Math.max(answer, friendCount);
        }
    }

    private static boolean isTwoFriend(int a, int b) {
        for (int k = 0; k < n; k++) {
            if (relations[a][k] == 'Y' && relations[k][b] == 'Y' && a != k && b != k) {
                return true;
            }
        }
        return false;
    }
}