import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String line = input.readLine();

        if (line == null || line.trim().isEmpty()) {
            System.out.println(0);
            return;
        }

        String[] words = line.trim().split("\\s+");
        System.out.println(words.length);
    }
}
