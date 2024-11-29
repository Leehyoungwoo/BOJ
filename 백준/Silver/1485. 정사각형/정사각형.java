import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int testcase;
    private static List<Point> list;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        testcase = Integer.parseInt(input.readLine());
        for (int i = 0; i < testcase; i++) {
            init(input);
            int n = findAnswer();
            answer.append(n).append("\n");
        }

        System.out.println(answer);
    }

    private static int findAnswer() {
        if (isRec()) {
            return 1;
        }
        return 0;
    }

    private static boolean isRec() {
        double[] distancesArr = new double[6];
        distancesArr[0] = distance(list.get(0).x,list.get(0).y, list.get(1).x, list.get(1).y);
        distancesArr[1] = distance(list.get(0).x,list.get(0).y, list.get(2).x, list.get(2).y);
        distancesArr[2] = distance(list.get(0).x,list.get(0).y, list.get(3).x, list.get(3).y);
        distancesArr[3] = distance(list.get(1).x,list.get(1).y, list.get(2).x, list.get(2).y);
        distancesArr[4] = distance(list.get(1).x,list.get(1).y, list.get(3).x, list.get(3).y);
        distancesArr[5] = distance(list.get(2).x,list.get(2).y, list.get(3).x, list.get(3).y);
        Arrays.sort(distancesArr);

        return distancesArr[0] == distancesArr[1]
                && distancesArr[1] == distancesArr[2]
                && distancesArr[2] == distancesArr[3]
                && distancesArr[4] == distancesArr[5];
    }

    private static double distance (int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    private static void init(BufferedReader input) throws IOException {
        list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            list.add(new Point(a, b));
        }
    }
}

class Point {
    int x;
    int y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}