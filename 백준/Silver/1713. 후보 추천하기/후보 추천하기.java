
import java.io.*;
import java.util.*;

public class Main {

    private static int n, m;
    private static int[] num;
    private static int[] likeCount = new int[101];
    private static int[] time = new int[101];

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        m = Integer.parseInt(input.readLine());
        num = new int[m];
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < m; i++) {
            num[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void findAnswer() {
        boolean[] inQueue = new boolean[101];
        Queue<Integer> que = new LinkedList<>();
        for (int i = 0; i < num.length; i++) {
            int idx = num[i];
            if (que.contains(idx)) {
                likeCount[idx]++;
                time[idx]++;
                continue;
            }
            if (que.size() < n) {
                inQueue[idx] = true;
                que.offer(idx);
            } else {
                int minCountIdx = 0;
                int min = Integer.MAX_VALUE;
                for (int s : que) {
                    if (min > likeCount[s]) {
                        minCountIdx = s;
                        min = likeCount[s];
                        continue;
                    }

                    if (min == likeCount[s]) {
                        if (time[s] < time[minCountIdx]) {
                            minCountIdx = s;
                        }
                    }
                }

                que.remove(minCountIdx);
                inQueue[minCountIdx] = false;
                likeCount[minCountIdx] = 0;

                time[idx] = i;
                inQueue[idx] = true;
                que.offer(idx);
            }
        }

        List<Integer> list = new ArrayList<>();
        while (!que.isEmpty()) {
            list.add(que.poll());
        }

        Collections.sort(list);

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            answer.append(list.get(i)).append(" ");
        }

        System.out.println(answer);
    }
}