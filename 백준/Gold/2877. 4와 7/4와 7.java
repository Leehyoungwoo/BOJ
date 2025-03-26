import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long K = Long.parseLong(br.readLine());

        int length = 1;
        long count = 0;

        while (true) {
            long currentLevelCount = 1L << length; 
            if (count + currentLevelCount >= K) break;
            count += currentLevelCount;
            length++;
        }

        long indexInLevel = K - count - 1; 

        StringBuilder sb = new StringBuilder();
        for (int i = length - 1; i >= 0; i--) {
            if ((indexInLevel & (1L << i)) == 0) sb.append("4");
            else sb.append("7");
        }

        System.out.println(sb);
    }
}