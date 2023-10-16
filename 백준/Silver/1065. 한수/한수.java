import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int answer = getCount(N);

        System.out.println(answer);
    }

    public static int getCount(int N) {
        int hanCnt = 0;
        if (N < 100) {
            hanCnt += N;
        }
        if (N >= 100) {
            hanCnt += 99;
            for (int size = N + 1, i = 100; i < size; i++) {
                if (DecideArithmeticProgression(i)){
                    hanCnt++;
                }
            }
        }
        return hanCnt;
    }

    public static boolean DecideArithmeticProgression(int N) {
        int[] numArr = convertCharToInt(N);

        if (numArr[0] - numArr[1] == numArr[1] - numArr[2]) {
            return true;
        } else {
            return false;
        }
    }

    public static int[] convertCharToInt(int N) {
        char[] ch = convertIntTOChar(N);
        int[] numArr = new int[ch.length];

        for (int size = ch.length, i = 0; i < size; i++) {
            numArr[i] = ch[i] - '0';
        }
        return numArr;
    }

    public static char[] convertIntTOChar(int N) {
        int pv = calculatePlaceValue(N);
        char[] ch = new char[pv];

        for (int size = pv, i = 0; i < size; i++) {
            ch[i] = Integer.toString(N).charAt(i);
        }
        return ch;
    }

    public static int calculatePlaceValue(int N) {
        int pv = (int) (Math.log10(N) + 1);
        return pv;
    }
}