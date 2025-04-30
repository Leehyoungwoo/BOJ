import java.io.*;
import java.util.*;

public class Main {

//    private static String line;
    private static int idx;
    private static char[] chs;
    private static int[] per;
    private static StringBuilder answer;
    private static int count;

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        answer = new StringBuilder();
        String line = "";
        while ((line = input.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) {
                // 빈 줄을 입력받으면 루프 탈출
                break;
            }
            init(line);
            dfs(0, new boolean[chs.length], line);
            if (count < idx) {
                answer.append(line).append(" = ");
                answer.append("No permutation");
            }
            answer.append("\n");
        }
        System.out.println(answer);
    }

    private static void init(String line) {
        String[] split = line.split(" ");
        idx = Integer.parseInt(split[1]);
        chs = split[0].toCharArray();
        per = new int[chs.length];
        count = 0;
    }

    private static void dfs(int start, boolean[] visited, String line) {
        if (start == chs.length) {
            count++;
            if (count == idx) {
                answer.append(line).append(" = ");
                for (int i = 0; i < per.length; i++) {
                    answer.append(chs[per[i]]);
                }
            }
            return;
        }

        for (int i = 0; i < chs.length; i++) {
            if (!visited[i]) {
                per[start] = i;
                visited[i] = true;
                dfs(start + 1, visited, line);
                visited[i] = false;
            }
        }
    }
}
