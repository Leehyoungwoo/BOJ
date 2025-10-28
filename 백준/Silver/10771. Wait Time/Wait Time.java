import java.io.*;
import java.util.*;

public class Main {

    private static int m;
    private static Map<Integer, Integer> friendWaitStart;
    private static Map<Integer, Integer> total;
    private static Map<Integer, Integer> count;
    private static String[] lines;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        m = Integer.parseInt(input.readLine());
        lines = new String[m];
        for (int i = 0; i < lines.length; i++) {
            lines[i] = input.readLine();
        }
        friendWaitStart = new HashMap<>();
        total = new HashMap<>();
        count = new HashMap<>();
    }

    private static void findAnswer() {
        int time = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Set<Integer> friends = new HashSet<>(); // ★ R 나온 친구들 기록

        for (int i = 0; i < lines.length; i++) {
            String[] line = lines[i].split(" ");
            String type = line[0];
            int friendId = Integer.parseInt(line[1]);

            if (type.equals("R")) {
                friends.add(friendId); // ★ 출력 대상
                friendWaitStart.put(friendId, time);
                count.put(friendId, count.getOrDefault(friendId, 0) + 1);
            } else if (type.equals("S")) {
                pq.add(friendId);
                total.put(friendId, total.getOrDefault(friendId, 0)
                        + time - friendWaitStart.get(friendId));
                count.put(friendId, count.getOrDefault(friendId, 0) - 1);
            } else { // W X
                time += friendId; // ★ X-1이 아니라 X 그대로
            }

            if (i + 1 < lines.length) {
                // ★ 다음 줄이 W가 아닐 때만 1초 증가
                if (lines[i + 1].charAt(0) != 'W') {
                    if (type.charAt(0) != 'W') { // ★ 현재줄이 W인 경우는 이미 반영됨
                        time++;
                    }
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        List<Integer> list = new ArrayList<>(friends); // ★ 친구번호 오름차순
        Collections.sort(list);

        for (int friendId : list) {
            if (count.getOrDefault(friendId, 0) > 0) {
                answer.append(friendId).append(" ").append(-1).append("\n"); // ★번호 출력 유지
            } else {
                int t = total.getOrDefault(friendId, 0);
                answer.append(friendId).append(" ").append(t).append("\n");
            }
        }

        System.out.print(answer);
    }
}