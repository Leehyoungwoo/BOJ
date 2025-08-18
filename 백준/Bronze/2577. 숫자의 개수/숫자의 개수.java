import java.io.*;
import java.util.*;

public class Main {

    private static int a;
    private static int b;
    private static int c;
    private static Map<Integer, Integer> map;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        a = Integer.parseInt(input.readLine());
        b = Integer.parseInt(input.readLine());
        c = Integer.parseInt(input.readLine());
        map = new HashMap<>();
    }

    private static void findAnswer() {
        String total = String.valueOf(a * b * c);
        for (int i = 0; i < total.length(); i++) {
            int c = total.charAt(i) - '0';
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            answer.append(map.getOrDefault(i, 0)).append("\n");
        }

        System.out.println(answer);
    }
}
