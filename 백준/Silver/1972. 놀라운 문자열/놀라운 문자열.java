import java.util.Scanner;
import java.util.HashMap;
 
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 입력값을 저장할 변수 선언
        String input;
        
        // '*'가 입력될 때까지 반복
        while (!(input = sc.nextLine()).equals("*")) {
            // 입력값이 Surprising한지 여부를 확인하고 결과 출력
            if (isSurprising(input)) {
                System.out.println(input + " is surprising.");
            } else {
                System.out.println(input + " is NOT surprising.");
            }
        }
    }
    
    // Surprising 여부를 확인하는 메서드
    private static boolean isSurprising(String s) {
        // 문자열 길이 - 1만큼 반복
        for (int d = 0; d < s.length() - 1; d++) {
            // 현재 길이 d에 대한 모든 문자 쌍을 저장할 HashMap 생성
            HashMap<String, Integer> pairs = new HashMap<>();
            
            // 문자열을 돌면서 길이 d인 모든 문자 쌍에 대해 검사
            for (int i = 0; i + d < s.length() - 1; i++) {
                // 현재 위치 i와 i+d+1에 있는 두 문자로 이루어진 문자 쌍 생성
                String pair = s.charAt(i) + "" + s.charAt(i + d + 1);
                
                // 이미 등장한 쌍인지 확인하고 등장했다면 Surprising하지 않음
                if (pairs.containsKey(pair)) {
                    return false;
                }
                // 등장하지 않은 쌍이면 HashMap에 추가
                pairs.put(pair, 1);
            }
        }
        
        // 모든 길이에 대해 Surprising하다면 true 반환
        return true;
    }
}