import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int n = 1000 - num;
        int[] coins = new int[]{500, 100, 50, 10, 5, 1};
        int cnt = 0;
        for (int i = 0; i < coins.length; i++) {
            if (n == 0) {
                break;
            }
            if (coins[i] > n) {
                continue;
            }
            cnt += n / coins[i];
            n %= coins[i];
        }
        System.out.println(cnt);
    }
}