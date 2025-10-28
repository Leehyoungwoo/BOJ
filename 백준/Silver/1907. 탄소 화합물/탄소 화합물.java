import java.io.*;
import java.util.*;

public class Main {

    private static String line;
    private static String first, second, sum;
    private static Map<Character, Integer> aMap;
    private static Map<Character, Integer> bMap;
    private static Map<Character, Integer> cMap;
    private static boolean found = false;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        line = input.readLine();
        first = line.split("\\+")[0];
        second = line.split("\\+")[1].split("=")[0];
        sum = line.split("=")[1];
        aMap = new HashMap<>();
        bMap = new HashMap<>();
        cMap = new HashMap<>();

        putMap(first, aMap);
        putMap(second, bMap);
        putMap(sum, cMap);
    }

    private static void findAnswer() {
        // 1이상 10이하면 그냥 완탐으로 구할까? 많이 해봐야 1000인데
        // 그게 맞는거 같다
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                for (int k = 1; k <= 10; k++) {
                    if (isPossible(i, j, k)) {
                        System.out.println(i + " " + j + " " + k);
                        return;
                    }
                }
            }
        }
    }

    private static void putMap(String str, Map<Character, Integer> map) {
        int prevNum = 0;
        char prevChar = '\0';
        for (char c : str.toCharArray()) {
            if ('2' <= c && c <= '9') {
                prevNum = c - '0';
                map.put(prevChar, map.getOrDefault(prevChar, 0) - 1 + (1 * prevNum));
                prevNum = 0;
                continue;
            }

            if (prevNum == 0) {
                map.put(c, map.getOrDefault(c, 0) + 1);
                prevChar = c;
            }
        }

    }

    private static boolean isPossible(int x1, int x2, int x3) {
        if (x1 == 0 || x2 == 0 || x3 == 0) return false;

        for (char c : sum.toCharArray()) {
            int cVal = cMap.getOrDefault(c, 0);
            int aVal = aMap.getOrDefault(c, 0);
            int bVal = bMap.getOrDefault(c, 0);
            if (x3 * cVal != x1 * aVal + x2 * bVal) {
                return false;
            }
        }

        return true;
    }
}