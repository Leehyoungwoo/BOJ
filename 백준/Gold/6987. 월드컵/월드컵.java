import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import java.util.StringTokenizer;

public class Main {

    private static int[][] temp;
    private static int[][] result;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            init(input);
            answer.append(isValidMatch(0,1) ? 1: 0).append(' ');
        }
        System.out.println(answer);
    }

    private static boolean isValidMatch(int home, int away) {
        if (home == 4 && away == 6) {
            return Arrays.deepEquals(temp, result);
        }
        if(away == 6) {
            return isValidMatch(home + 1, home + 2);
        }

        boolean valid = false;
        //Home Win
        temp[home][0]++;
        temp[away][2]++;
        valid = isValidMatch(home, away + 1);
        temp[home][0]--;
        temp[away][2]--;

        //Draw
        if(!valid) {
            temp[home][1]++;
            temp[away][1]++;
            valid = isValidMatch(home, away + 1);
            temp[home][1]--;
            temp[away][1]--;
        }

        //Home lose
        if(!valid){
            temp[home][2]++;
            temp[away][0]++;
            valid = isValidMatch(home, away + 1);
            temp[home][2]--;
            temp[away][0]--;
        }

        return valid;
    }

    private static void init(BufferedReader input) throws IOException {
        temp = new int[6][3];
        result = new int[6][3];

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
    }
}