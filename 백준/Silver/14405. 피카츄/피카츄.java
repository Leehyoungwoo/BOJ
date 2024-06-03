import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();

        // 정규식 패턴 생성
        String regex = "(pi|ka|chu)+"; // "pi", "ka", "chu" 중 하나 이상이 연속으로 반복되는 패턴
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(S);

        // 정규식 매칭 결과 확인
        if (matcher.matches()) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}