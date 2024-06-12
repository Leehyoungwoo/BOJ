import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static String startTime;
    private static String endTime;
    private static String realEnd;
    private static Queue<String> log = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String[] times = input.readLine().split(" ");
        startTime = times[0];
        endTime = times[1];
        realEnd = times[2];
        String line;
        while ((line = input.readLine()) != null && !line.isEmpty()) {
            log.offer(line);
        }
    }

    private static void findAnswer() {
        Set<String> in = new HashSet<>();
        Set<String> out = new HashSet<>();
        while (!log.isEmpty()) {
            String line = log.poll();
            String[] s = line.split(" ");
            String times = s[0];
            String name = s[1];

            if (times.compareTo(startTime) <= 0) {
                in.add(name);
            } else if (times.compareTo(endTime) >= 0 && times.compareTo(realEnd) <= 0) {
                if (in.contains(name)) {
                    out.add(name);
                }
            }
        }
        System.out.println(out.size());
    }
}