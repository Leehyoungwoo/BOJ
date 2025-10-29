import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    // 백신 접종 여부, A, B, C 감염 여부
    private static String[] people;

    public static void main(String[] args) throws IOException {
        init();
        String answer = findAnswer();

        System.out.println(answer);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        people = new String[n];
        for (int i = 0; i < n; i++) {
            people[i] = input.readLine();
        }
    }

    private static String findAnswer() {
        StringBuilder answer = new StringBuilder();
        int iCount = 0;
        Map<Character, Integer> injected = new HashMap<>();
        int cCount = 0;
        Map<Character, Integer> contra = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String p = people[i];
            char yesOrNo = people[i].charAt(0);
            char a = people[i].charAt(1);
            char b = people[i].charAt(2);
            char c = people[i].charAt(3);
            if (yesOrNo == 'Y') {
                iCount++;
                if (a == 'Y') {
                    injected.put('A', injected.getOrDefault('A', 0) + 1);
                }
                if (b == 'Y') {
                    injected.put('B', injected.getOrDefault('B', 0) + 1);
                }

                if (c == 'Y') {
                    injected.put('C', injected.getOrDefault('C', 0) + 1);
                }
            } else {
                cCount++;
                if (a == 'Y') {
                    contra.put('A', contra.getOrDefault('A', 0) + 1);
                }

                if (b == 'Y') {
                    contra.put('B', contra.getOrDefault('B', 0) + 1);
                }

                if (c == 'Y') {
                    contra.put('C', contra.getOrDefault('C', 0) + 1);
                }
            }
        }
        //감염률 비교
        for (char c = 'A'; c <= 'C'; c++) {
            double iRate = 100.0 * injected.getOrDefault(c, 0) / iCount;
            double cRate = 100.0 * contra.getOrDefault(c, 0) / cCount;
            if (iRate >= cRate) {
                answer.append("Not Effective").append("\n");
            } else {
                double efficacy = 100 * (cRate - iRate)/ cRate;
                answer.append(String.format("%.6f", efficacy)).append("\n");

            }
        }

        return answer.toString();
    }
}