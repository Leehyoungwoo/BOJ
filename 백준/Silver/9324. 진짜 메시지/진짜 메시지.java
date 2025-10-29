import java.io.*;
import java.util.*;

public class Main {

    private static int testcase;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        testcase = Integer.parseInt(input.readLine());
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < testcase; i++) {
            String line = input.readLine();
            String valid = isValid(line);
            answer.append(valid).append("\n");
        }

        System.out.println(answer);
    }

    private static String isValid(String line) {
        Map<Character, Integer> count = new HashMap<>();
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            count.put(c, count.getOrDefault(c, 0) + 1);
            if (count.get(c) % 3 == 0) {
                if (i + 1 >= line.length() || line.charAt(i + 1) != c) {
                    return "FAKE";
                }
                i = i + 1;
            }
        }

        return "OK";
    }
}