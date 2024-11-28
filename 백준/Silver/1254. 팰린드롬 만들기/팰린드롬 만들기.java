import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int n = s.length();

        int minLength = n;  // 최소 팰린드롬 길이 (초기값 = 원래 길이)
        for (int i = 0; i < n; i++) {
            if (isPalindrome(s.substring(i))) {
                minLength = n + i;  // 앞의 i 문자들을 추가한 길이로 팰린드롬 가능
                break;
            }
        }
        System.out.println(minLength);
    }

    // 팰린드롬 판별 함수
    private static boolean isPalindrome(String str) {
        int left = 0, right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}