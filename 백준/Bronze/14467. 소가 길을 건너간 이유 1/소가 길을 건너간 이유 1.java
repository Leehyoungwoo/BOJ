import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(input.readLine());
        Map<Integer, Integer> count = new HashMap<>();
        Map<Integer, Integer> cowStatus = new HashMap<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int cow = Integer.parseInt(tokenizer.nextToken());
            int road = Integer.parseInt(tokenizer.nextToken());
            if (!cowStatus.containsKey(cow)) {
                cowStatus.put(cow, road);
                count.put(cow, 0);
            } else {
                if (cowStatus.get(cow) == road) {
                    continue;
                }
                count.put(cow, count.get(cow) + 1);
                cowStatus.put(cow, road);
            }
        }

        int sum = 0;
        for(Map.Entry<Integer, Integer> entry : count.entrySet()) {
            if (entry.getValue() == 0) {
                continue;
            }
            sum += entry.getValue();
        }

        System.out.println(sum);
    }
}
