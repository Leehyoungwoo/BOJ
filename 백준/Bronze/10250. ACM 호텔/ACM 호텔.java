import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken()); // 계산엔 안 쓰지만 입력 형식상 읽어둠
            int n = Integer.parseInt(st.nextToken());

            int floor = (n - 1) % h + 1;
            int room  = (n - 1) / h + 1;

            sb.append(floor * 100 + room).append('\n');
        }

        System.out.print(sb);
    }
}
