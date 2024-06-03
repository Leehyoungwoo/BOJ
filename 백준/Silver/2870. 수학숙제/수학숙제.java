import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(input.readLine());

        List<String> numbers = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\d+");

        for (int i = 0; i < N; i++) {
            String line = input.readLine();
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                String number = matcher.group().replaceAll("^0+", "");
                numbers.add(number.length() == 0 ? "0" : number);
            }
        }
        numbers.sort((o1, o2) -> o1.length() == o2.length() ? o1.compareTo(o2) : o1.length() - o2.length());

        for (String number : numbers) {
            System.out.println(number);
        }
    }
}