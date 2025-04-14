import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static Queue<String> inputs;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        inputs = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            String line = input.readLine();
            inputs.offer(line);
        }
    }

    private static void findAnswer() {
        StringBuilder answer = new StringBuilder();
        Map<String, String> map = new HashMap<>();
        while (!inputs.isEmpty()) {
            String line = inputs.poll();
            String key = "" + line.charAt(0);
            // 첫 문자로 가능해?
            if (!map.containsKey(key.toLowerCase()) && !map.containsKey(key.toUpperCase())) {
                map.put(key, line);
                answer.append("[" + key + "]" + line.substring(1) + "\n");
                continue;
            }

            // 안되면 다음 문자로 넘어가?
            String[] words = line.split(" ");
            boolean found = false;
            for (int i = 1; i < words.length; i++) {
                String c = "" + words[i].charAt(0);
                if (!map.containsKey(c.toLowerCase()) && !map.containsKey(c.toUpperCase())) {
                    map.put(c, line);
                    for (int j = 0; j < words.length; j++) {
                        if (i == j) {
                            answer.append("[" + c + "]" + words[i].substring(1) + " ");
                            continue;
                        }
                        answer.append(words[j] + " ");
                    }
                    found = true;
                }

                if (found) {
                    break;
                }
            }

            // 찾았으면 넘어가야지
            if (found) {
                answer.append("\n");
                continue;
            }

            // 끝까지 갔는데 안되면 인덱스를 옮기면서 가야해?
            found = false;
            for (int i = 0; i < line.length(); i++) {
                String k = "" + line.charAt(i);
                if (k.equals(" ")) {
                    continue;
                }
                if (!map.containsKey(k.toLowerCase()) && !map.containsKey(k.toUpperCase())) {
                    map.put(k, line);
                    found = true;
                    for (int j = 0; j < line.length(); j++) {
                        if (i == j) {
                            answer.append("[" + k + "]");
                            continue;
                        }

                        answer.append(line.charAt(j));
                    }
                }
                if (found) {
                    break;
                }
            }

            if (found) {
                answer.append("\n");
                continue;
            }
            answer.append(line).append("\n");
        }

        System.out.println(answer);
    }
}