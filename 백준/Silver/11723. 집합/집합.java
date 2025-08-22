import java.io.*;
import java.util.*;

public class Main {

    private static int m;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        init();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        m = Integer.parseInt(input.readLine());
        visited = new boolean[21];
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < m; i++) {
            String[] s  = input.readLine().split(" ");
            findAnswer(s, answer);
        }

        System.out.println(answer);
    }


    private static void findAnswer(String[] s, StringBuilder answer) {
        if (s[0].equals("add")) {
            visited[Integer.parseInt(s[1])] = true;
            return;
        }

        if (s[0].equals("remove")) {
            visited[Integer.parseInt(s[1])] = false;
            return;
        }

        if (s[0].equals("check")) {
            if (!visited[Integer.parseInt(s[1])]) {
                answer.append("0").append("\n");
            } else {
                answer.append("1").append("\n");
            }
            return;
        }

        if (s[0].equals("toggle")) {
            if (!visited[Integer.parseInt(s[1])]) {
                visited[Integer.parseInt(s[1])] = true;
            } else {
                visited[Integer.parseInt(s[1])] = false;
            }
            return;
        }

        if (s[0].equals("all")) {
            for (int i = 1; i <= 20; i++) {
                visited[i] = true;
            }

            return;
        }
        if (s[0].equals("empty")) {
            for (int i = 0; i <= 20; i++) {
                visited[i] = false;
            }
        }
    }
}