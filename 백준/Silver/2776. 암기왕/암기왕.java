import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int t;
    private static int n;
    private static int m;
    private static Set<Integer> note1;
    private static int[] note2;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(input.readLine());
        StringBuilder finalAnswer = new StringBuilder();
        for (int i = 0; i < t; i++) {
            finalAnswer.append(init(input));
        }
        System.out.print(finalAnswer);
    }

    private static StringBuilder init(BufferedReader input) throws IOException {
        n = Integer.parseInt(input.readLine());
        note1 = new HashSet<>();
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            note1.add(Integer.parseInt(tokenizer.nextToken()));
        }
        m = Integer.parseInt(input.readLine());
        note2 = new int[m];
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < m; i++) {
            note2[i] = Integer.parseInt(tokenizer.nextToken());
        }
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < m; i++) {
            if (note1.contains(note2[i])) {
                answer.append(1).append("\n");
                continue;
            }
            answer.append(0).append("\n");
        }
        return answer;
    }
}