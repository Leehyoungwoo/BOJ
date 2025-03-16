import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n;
    private static List<String> inputs;
    // 가중치 저장해서 분배
    private static Map<Character, Integer> map;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        inputs = new ArrayList<>();
        map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String line = input.readLine();
            inputs.add(line);
            for (int j = 0; j < line.length(); j++) {
                char c = line.charAt(j);
                int v = (int) Math.pow(10, line.length() - j - 1);
                map.put(c, map.getOrDefault(c, 0) + v);
            }
        }
    }

    private static void findAnswer() {
        PriorityQueue<Integer> numbering = new PriorityQueue<>(Collections.reverseOrder());
        // 0-9까지 집어넣기
        for (int i = 0; i < 10; i++) {
            numbering.offer(i);
        }

        PriorityQueue<int[]> alphabet = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        // 가중치 내림차순으로 정렬되어 있는 우선순위 큐
        for (Map.Entry<Character, Integer> entry: map.entrySet()) {
            int v = entry.getValue();
            int idx = entry.getKey() - 'A';
            alphabet.offer(new int[] {v, idx});
        }

        // 알파벳 별 배정 숫자 저장해주고
        Map<Character, Integer> translate = new HashMap<>();
        while (!alphabet.isEmpty()) {
            int[] cur = alphabet.poll();
            int valueNumber = numbering.poll();
            char alpha = (char) ('A' + cur[1]);
            translate.put(alpha, valueNumber);
        }

        // 번역해서 list에 넣어주고
        List<Integer> translated = new ArrayList<>();
        for (int i = 0; i < inputs.size(); i++) {
            String line = inputs.get(i);
            StringBuilder trans = new StringBuilder();
            for (int j = 0; j < line.length(); j++) {
                char c = line.charAt(j);
                int t = translate.get(c);
                trans.append(t);
            }
            translated.add(Integer.parseInt(trans.toString()));
        }

        int sum = translated.stream().mapToInt(i -> i).sum();
        System.out.println(sum);
    }
}
