import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
       BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
       String[] s = input.readLine().split(" ");
       int A = Integer.parseInt(s[0]);
       int B = Integer.parseInt(s[1]);
       
       System.out.println(A * B);
    }
}