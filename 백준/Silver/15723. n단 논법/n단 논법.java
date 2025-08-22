import java.io.*;
import java.util.*;

public class Main {

    private static int n, m;
    private static boolean[][] dist;
    private static List<String> inputs;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        dist = new boolean[26][26];
        inputs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] s = input.readLine().split(" is ");
            int a = s[0].charAt(0) - 'a';
            int b = s[1].charAt(0) - 'a';
            dist[a][b] = true;
        }

        m = Integer.parseInt(input.readLine());
        for (int i = 0; i < m; i++) {
            inputs.add(input.readLine());
        }
    }

    private static void findAnswer() {
        StringBuilder answer = new StringBuilder();
        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    dist[i][j] = dist[i][j] || (dist[i][k] && dist[k][j]);
                }
            }
        }

        for (int i = 0; i < inputs.size(); i++) {
            String[] s = inputs.get(i).split(" is ");
            int a = s[0].charAt(0) - 'a';
            int b = s[1].charAt(0) - 'a';
            if (dist[a][b]) {
                answer.append("T");
            } else {
                answer.append("F");
            }
            answer.append("\n");
        }

        System.out.println(answer);
    }
}