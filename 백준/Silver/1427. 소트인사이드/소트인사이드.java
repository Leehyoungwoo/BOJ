import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String target = input.readLine();
        int[] arr = new int[target.length()];
        for (int i = 0; i < target.length(); i++) {
            arr[i] = target.charAt(i) - '0';
        }
        sort(arr);
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            answer.append(arr[i]);
        }
        System.out.println(answer);
    }

    private static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int max = Integer.MIN_VALUE;
            int idx = -1;
            for (int j = i; j < arr.length; j++) {
                if(arr[j] > max) {
                    max = arr[j];
                    idx = j;
                }
            }
            int temp = arr[i];
            arr[i] = max;
            arr[idx] = temp;
        }
    }
}