import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        String[] strArr = new String[n];
        
        for (int i = 0; i < n; i++) {
            strArr[i] = br.readLine();
        }
        
        // 문자열 길이를 기준으로 내림차순으로 정렬
        Arrays.sort(strArr, new Comparator<String>() {
            public int compare(String s1, String s2) {
                return Integer.compare(s2.length(), s1.length());
            }
        });
        
        HashSet<String> set = new HashSet<>();
        for (String s1 : strArr) {
            boolean isPrefix = false;
            // set에 있는 문자열 중에 s1이 접두어인지 확인
            for (String s2 : set) {
                if (s2.startsWith(s1)) {
                    isPrefix = true;
                    break;
                }
            }
            if (!isPrefix) {
                set.add(s1);
            }
        }
        
        System.out.println(set.size());
    }
}