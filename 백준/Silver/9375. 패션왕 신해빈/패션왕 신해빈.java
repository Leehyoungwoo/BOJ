import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int t;
    private static int n;
    private static StringBuilder answer = new StringBuilder();
    private static Map<String, List<String>> map = new HashMap<>();


    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(input.readLine());
        for (int i = 0; i < t; i++) {
            n = Integer.parseInt(input.readLine());
            map = new HashMap<>();
            findAnswer(input);
        }
        System.out.println(answer);
    }

    private static void findAnswer(BufferedReader input) throws IOException {
        for (int i = 0; i < n; i++) {
            String[] s = input.readLine().split(" ");
            String cloth = s[0];
            String type = s[1];
            if (!map.containsKey(type)) {
                map.put(type, new ArrayList<>());
            }
            map.get(type).add(cloth);
        }
        int comb = 1;
        for (String key : map.keySet()) {
            comb *= (map.get(key).size() + 1);
        }
        answer.append(comb - 1).append("\n");
    }
}