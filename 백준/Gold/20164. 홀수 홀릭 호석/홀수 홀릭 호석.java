import java.io.*;
import java.util.*;

public class Main {

    private static String n;
    private static int min = Integer.MAX_VALUE;
    private static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = input.readLine();
    }

    private static void findAnswer() {
        // 수의 각 자리 숫자 중에서 홀수의 개수를 종이에 적는다.
        int initCount = 0;
        for (int i = 0; i < n.length(); i++) {
            int each = n.charAt(i) - '0';
            if (each % 2 != 0) {
                initCount++;
            }
        }
        // DFS로 전역 변수 max와 min을 종료 조건에 갱신해서 비교해봐야 할 거 같은데
        findMixAndMax(n, 0);
        min += initCount;
        max += initCount;
        System.out.println(min + " " + max);
    }

    private static void findMixAndMax(String number, int oddCount) {
        // 수가 한 자리이면 더 이상 아무것도 하지 못하고 종료한다.
        if (number.length() == 1) {
            min = Math.min(oddCount, min);
            max = Math.max(oddCount, max);
            return;
        }
        // 수가 두 자리이면 2개로 나눠서 합을 구하여 새로운 수로 생각한다.
        if (number.length() == 2) {
            int first = number.charAt(0) - '0';
            int second = number.charAt(1) - '0';
            int oc = 0;
            for (int i = 0; i < Integer.toString(first + second).length(); i++) {
                if ((Integer.toString(first + second).charAt(i) - '0') % 2 != 0) {
                    oc++;
                }
            }
            findMixAndMax(Integer.toString(first + second), oddCount + oc);
            return;
        }
        // 수가 세 자리 이상이면 임의의 위치에서 끊어서 3개의 수로 분할하고, 3개를 더한 값을 새로운 수로 생각한다.
        // 00000 0 - 4
        for (int i = 1; i <= number.length() - 2; i++) {
            for (int j = i + 1; j < number.length(); j++) {
                int first = Integer.parseInt(number.substring(0, i));
                int second = Integer.parseInt(number.substring(i, j));
                int third = Integer.parseInt(number.substring(j));
                int sum = first + second + third;
                int oc = 0;
                for (int each = 0; each < Integer.toString(sum).length(); each++) {
                    if ((Integer.toString(sum).charAt(each) - '0') % 2 != 0) {
                        oc++;
                    }
                }
                findMixAndMax(Integer.toString(sum), oddCount + oc);
            }
        }
    }
}