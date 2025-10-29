import java.io.*;
import java.util.StringTokenizer;

public class Main {

    private static int set;
    private static String mine;
    private static String[] gen;
    private static int n, k;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        set = Integer.parseInt(input.readLine());
        StringBuilder answer = new StringBuilder();

        for (int i = 1; i <= set; i++) {
            init(input);
            decorator(answer, i);
            findAnswer(answer);
            answer.append("\n");
        }

        System.out.println(answer);
    }

    private static void decorator(StringBuilder answer, int set) {
        String format = "Data Set";
        answer.append(format).append(" ").append(set).append(":").append("\n");
    }

    private static void findAnswer(StringBuilder answer) {
        int count = 0;
        for (int i = 0; i < k; i++) {
            char my = mine.charAt(i);
            for (int j = 0; j < n; j++) {
                char old = gen[j].charAt(i);
                if (my == old) {
                    count++;
                    break;
                }
            }
        }

        answer.append(k - count).append("/").append(k).append("\n");
    }

    private static void init(BufferedReader input) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());
        gen = new String[n];
        mine = input.readLine();
        for (int i = 0; i < n; i++) {
            gen[i] = input.readLine();
        }
    }
}