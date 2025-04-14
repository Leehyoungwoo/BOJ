import java.io.*;
import java.util.*;

public class Main {

    private static int n, m;
    private static Map<Integer, Integer> pointMap;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        String line;
        while(!(line = input.readLine()).equals("0 0")) {
            StringTokenizer tokenizer = new StringTokenizer(line);
            n = Integer.parseInt(tokenizer.nextToken());
            m = Integer.parseInt(tokenizer.nextToken());
            pointMap = new HashMap<>();
            for (int i = 0; i < n; i++) {
                tokenizer = new StringTokenizer(input.readLine());
                for (int j = 0; j < m; j++) {
                    int member = Integer.parseInt(tokenizer.nextToken());
                    pointMap.put(member, pointMap.getOrDefault(member, 0) + 1);
                }
            }

            int secondScore = 0;
            List<Integer> scoreList = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : pointMap.entrySet()) {
                scoreList.add(entry.getValue());
            }
            Collections.sort(scoreList);
            secondScore = scoreList.get(scoreList.size() - 2);

            List<Integer> secondMemberList = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : pointMap.entrySet()) {
                if (entry.getValue() == secondScore) {
                    secondMemberList.add(entry.getKey());
                }
            }

            Collections.sort(secondMemberList);
            for (int i : secondMemberList) {
                answer.append(i).append(" ");
            }

            answer.append("\n");
        }

        System.out.println(answer);
    }
}
