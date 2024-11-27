import java.util.*;
import java.io.*;

public class Main {

    private static int n;
    private static int[] memory;
    private static int[] answer;

    public static void main(String[] args) throws IOException {
        init();
        StringBuilder builder = new StringBuilder();
        findAnswer();
        Arrays.stream(answer).forEach(a -> builder.append(a).append(" "));
        System.out.println(builder);
    }

    private static void findAnswer() {
        answer = new int[n];
        for (int i = 1; i <= n; i++) {
            int count = memory[i - 1];
            int idx = 0;
            while (count != 0 || answer[idx] != 0) {
                if(answer[idx] == 0) {
                    count --;
                }
                idx++;
            }
            answer[idx] = i;
        }
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        memory = new int[n];
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            memory[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }
}