import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        String regexp = "FBI";
        Pattern pattern = Pattern.compile(regexp);
        for (int i = 1; i <= 5; i++) {
            String agentName = input.readLine();
            Matcher matcher = pattern.matcher(agentName);
            if (matcher.find()) {
                answer.append(i).append("\n");
            }
        }
        if (answer.length() == 0) {
            System.out.println("HE GOT AWAY!");
            return;
        }

        System.out.println(answer);
    }
}