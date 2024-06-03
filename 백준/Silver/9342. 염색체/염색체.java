import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        String regex = "^[A-F]?A+F+C+[A-F]?$";
        Pattern pattern = Pattern.compile(regex);
        int TC = Integer.parseInt(input.readLine());
        for (int i = 0; i < TC; i++) {
            String target = input.readLine();
            if (pattern.matcher(target).find()) {
                answer.append("Infected!").append("\n");
            } else {
                answer.append("Good").append("\n");
            }
        }

        System.out.println(answer);
    }
}