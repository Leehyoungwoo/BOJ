import java.io.*;
import java.util.*;

public class Main {

    private static Map<Character, Integer> map;
    private static String line;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        line = input.readLine();
        map = new HashMap<>();
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (map.containsKey(c)) {
                continue;
            }
            map.put(c, i);
        }
    }

    private static void findAnswer() {
        StringBuilder answer = new StringBuilder();
        for (char c = 'a';  c <= 'z'; c++) {
            if (!map.containsKey(c)) {
                answer.append(-1).append(" ");
                continue;
            }

            answer.append(map.get(c)).append(" ");
        }

        System.out.println(answer);
    }
}