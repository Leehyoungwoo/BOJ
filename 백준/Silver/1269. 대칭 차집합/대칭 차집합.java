import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String[] s = input.readLine().split(" ");
        int n1 = Integer.parseInt(s[0]);
        int n2 = Integer.parseInt(s[1]);
        Map<Integer, Integer> map = new HashMap<>();
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n1; i++) {
            Integer num = Integer.parseInt(tokenizer.nextToken());
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n2; i++) {
            Integer num = Integer.parseInt(tokenizer.nextToken());
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int cnt = 0;
        for (Integer num : map.keySet()) {
            if (map.get(num) == 1) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}