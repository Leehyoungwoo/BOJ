import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static String[] line;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        line = new String[n];
        for (int i = 0; i < n; i++) {
            line[i] = input.readLine();
        }
    }

    private static void findAnswer() {
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < n; i++) {
            String[] str = line[i].split(" ");
            int m = Integer.parseInt(str[0]);
            for (int j = 0; j < str[1].length(); j++) {
                char c = str[1].charAt(j);
                for (int k = 0; k < m; k++) {
                    answer.append(c);
                }
            }
            answer.append("\n");
        }

        System.out.println(answer);
    }
}