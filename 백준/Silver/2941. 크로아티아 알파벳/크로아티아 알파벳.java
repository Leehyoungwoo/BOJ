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
        String regex = "c=|c-|dz=|d-|lj|nj|s=|z=";
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String target = input.readLine();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(target);

        int count = 0;
        while (matcher.find()) {
            count++;
        }

        count += target.replaceAll("c=|c-|dz=|d-|lj|nj|s=|z=", "").length();

        System.out.println(count);
    }
}