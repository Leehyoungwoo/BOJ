import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static int n;
    private static int m;
    private static char[][] map;
    private static boolean[][][] visited;
    private static Queue<Person> que = new LinkedList<>();
    private static int answer = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        visited = new boolean[n][m][1 << 6]; // 비트마스킹을 위해 3차원 배열 선언

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == '0') {
                    que.offer(new Person(i, j, 0, 0)); // 시작 위치와 이동 횟수, 키 상태를 큐에 추가
                }
            }
        }

        while (!que.isEmpty()) {
            Person person = que.poll();

            if (map[person.r][person.c] == '1') {
                answer = person.move;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nr = person.r + direction[i][0];
                int nc = person.c + direction[i][1];

                if (nr >= 0 && nc >= 0 && nr < n && nc < m && map[nr][nc] != '#' && !visited[nr][nc][person.key]) {
                    // 문인 경우
                    if (map[nr][nc] >= 'A' && map[nr][nc] <= 'F') {
                        if ((person.key & (1 << (map[nr][nc] - 'A'))) != 0) { // 해당 문의 키가 있는지 확인
                            visited[nr][nc][person.key] = true;
                            que.offer(new Person(nr, nc, person.move + 1, person.key));
                        }
                    } else {
                        int newKey = person.key;
                        // 키인 경우
                        if (map[nr][nc] >= 'a' && map[nr][nc] <= 'f') {
                            newKey |= (1 << (map[nr][nc] - 'a')); // 키를 얻음
                        }
                        visited[nr][nc][newKey] = true;
                        que.offer(new Person(nr, nc, person.move + 1, newKey));
                    }
                }
            }
        }

        System.out.println(answer);
    }
}

class Person {
    int r, c, move, key;

    Person(int r, int c, int move, int key) {
        this.r = r;
        this.c = c;
        this.move = move;
        this.key = key;
    }
}