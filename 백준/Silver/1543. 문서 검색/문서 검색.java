import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String target = input.readLine();
        String regexp = input.readLine();
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(target);
        int count = 0;
        int pos = 0;
        while (matcher.find(pos)) {
            pos = matcher.end();
            count++;
        }

        System.out.println(count);
    }
}