import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        Pattern vowelPattern = Pattern.compile("[aeiouAEIOU]");
        while (true) {
            String line = input.readLine();
            if (line.equals("#")) {
                break;
            }
            Matcher matcher = vowelPattern.matcher(line);
            long vowelCount = matcher.results().count();
            answer.append(vowelCount).append("\n");
        }
        System.out.println(answer);
    }
}