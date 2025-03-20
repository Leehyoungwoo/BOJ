import java.io.*;
import java.util.*;

public class Main {

    private static int n, m;
    private static int[] num;
    private static List<Integer> list;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        num = new int[n];
        list = new ArrayList<>();
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void findAnswer() {
        int left = Arrays.stream(num).max().getAsInt();
        int right = Arrays.stream(num).sum();
        int result = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canDivide(mid)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(result);

        StringBuilder second = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            second.append(list.get(i)).append(" ");
        }
        System.out.println(second);
    }

    private static boolean canDivide(int maxSum) {
        int count = 1;
        int divSum = 0;
        int divCount = 0;
        List<Integer> answerList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (divSum + num[i] > maxSum) {
                answerList.add(divCount);
                divSum = 0;
                divCount = 0;
                count++;
                if (count > m) {
                    return false;
                }
            }

            divSum += num[i];
            divCount++;
        }

        answerList.add(divCount);

        while (answerList.size() < m) {
            for (int i = 0; i < answerList.size(); i++) {
                if (answerList.get(i) > 1) {
                    int split = answerList.remove(i);
                    answerList.add(i, split - 1);
                    answerList.add(i + 1, 1);
                    break;
                }
            }
        }

        list.clear();
        list.addAll(answerList);

        return list.size() == m;
    }
}