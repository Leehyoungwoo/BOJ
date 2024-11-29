import java.io.*;
import java.util.*;

public class Main {

    private static final String stillWar = "SYJKGW";
    private static long n;
    private static long t;
    private static Map<Long, Long> map;
    private static long maxKey;
    private static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Long.parseLong(input.readLine());
        for (int i = 0; i < n; i++) {
            init(input);
            long maxValue = findAnswer();
            String s = maxValue > t / 2 ? String.valueOf(maxKey) : stillWar;
            answer.append(s).append("\n");
        }

        System.out.println(answer);
    }

    private static long findAnswer() {
        long maxV = Long.MIN_VALUE;
        for (Map.Entry<Long, Long> entry : map.entrySet()) {
            if (maxV < entry.getValue()) {
                maxV = entry.getValue();
                maxKey = entry.getKey();
            }
        }
        return maxV;
    }

    private static void init(BufferedReader input) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        t = Integer.parseInt(tokenizer.nextToken());
        map = new HashMap<>();
        for (int i = 0; i < t; i++) {
            long a = Long.parseLong(tokenizer.nextToken());
            map.put(a, map.getOrDefault(a, 0L) + 1);
        }
        maxKey = -1;
    }
}