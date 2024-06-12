import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        String[] firstLine = input.readLine().split(" ");
        int K = Integer.parseInt(firstLine[0]);
        int L = Integer.parseInt(firstLine[1]);

        LinkedHashSet<String> waitList = new LinkedHashSet<>();

        for (int i = 0; i < L; i++) {
            String studentID = input.readLine();
            if (waitList.contains(studentID)) {
                waitList.remove(studentID);
            }
            waitList.add(studentID);
        }

        int count = 0;
        for (String id : waitList) {
            System.out.println(id);
            count++;
            if (count == K) break; 
        }
    }
}