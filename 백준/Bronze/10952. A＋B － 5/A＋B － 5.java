import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        while (true) {
            String line = input.readLine();
            if (line.equals("0 0")) break;

            StringTokenizer tokenizer = new StringTokenizer(line);
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            answer.append(a + b).append("\n");
        }

        System.out.print(answer);
    }
}