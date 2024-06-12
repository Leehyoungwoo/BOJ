import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Integer> map = new HashMap<>();
        int n = Integer.parseInt(input.readLine());
        for (int i = 0; i < n; i++) {
            String runner = input.readLine();
            map.put(runner, map.getOrDefault(runner, 0) + 1);
        }
        Map<String, Integer> finished = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            String runner = input.readLine();
            finished.put(runner, finished.getOrDefault(runner, 0) + 1);
        }

        for (String s : map.keySet()) {
            if (map.get(s) > finished.getOrDefault(s, 0)) {
                System.out.println(s);
                break;
            }
        }
    }
}