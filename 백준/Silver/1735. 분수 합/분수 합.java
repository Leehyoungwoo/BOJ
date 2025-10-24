import java.io.*;
import java.util.*;

public class Main {

    private static long aSon, aPar;
    private static long bSon, bPar;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new  BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        aSon = Long.parseLong(tokenizer.nextToken());
        aPar = Long.parseLong(tokenizer.nextToken());
        tokenizer = new StringTokenizer(input.readLine());
        bSon = Long.parseLong(tokenizer.nextToken());
        bPar = Long.parseLong(tokenizer.nextToken());
    }

    private static void findAnswer() {
        // 분수 합은 최소공배수 구해서 분모 구하고
        // 분자는 최대공약수를 구하기 위한 값을 곱해서 더해주자
        long gcd = findGcd(aPar, bPar);
        long sumPar = aPar / gcd * bPar;
        long sumSon = aSon * (sumPar / aPar) + bSon * (sumPar / bPar);
        long g2 = findGcd(Math.abs(sumSon), sumPar);
        sumSon /= g2;
        sumPar /= g2;
        System.out.println(sumSon + " " + sumPar);
    }

    private static long findGcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }
}