import java.io.*;
import java.util.*;

public class Main {

    private static int n, m, k;
    private static int[] card;
    private static int[] chulsu;
    private static Map<Integer, Integer> indexMap;

    public static void main(String[] args) throws IOException {
        init();
        String answer = findAnswer();

        System.out.println(answer);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());
        indexMap = new HashMap<>();
        card = new int[m];
        chulsu = new int[k];
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < m; i++) {
            card[i] = Integer.parseInt(tokenizer.nextToken());
        }

        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < k; i++) {
            chulsu[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static String findAnswer() {
        // N개의 빨간색 카드가 있다. 각각의 카드는 순서대로 1부터 N까지 번호 -> 이 중에서 M개의 카드
        // N개의 파란색 카드가 있다. 각각의 카드는 순서대로 1부터 N까지 번호가 매겨져 있다. 이 중에서 빨간색에서 고른 번호와 같은 파란색 카드 M
        // 철수는 빨간색 카드를 가지고 민수는 파란색 카드
        // 철수와 민수는 고른 카드 중에 1장을 뒤집어진 상태로 냄 -> 카드를 다시 뒤집어서 번호가 큰 사람이 이김 -> 동작을 K번 해서 더 많이 이긴 사람이 최종적으로 승리 -> 한 번 낸 카드는 반드시 버림
        // 철수는 뛰어난 마술사이기 때문에 본인이 낼 카드를 마음대로 조작 -> 카드를 버리고 민수 몰래 다시 들고 온다거나 민수한테 없는 카드
        // 민수는 뛰어난 심리학자이기 때문에 철수가 낼 카드를 알아냄 -> 민수는 철수가 낼 카드보다 큰 카드가 있다면 그 카드들 중 가장 작은 카드
        // K번 동안 철수가 낼 카드가 입력 ->  민수가 어떤 카드를 낼지 출력

        // 민수랑 철수는 각각 card 배열의 숫자 카드 소유, 철수가 낼 카드는 정해져있(그래서 1을 두번 내내)
        // 민수의 픽이 중요한데 민수는 어퍼 바운드(철수가 낼 카드를 알고, 철수가 낼 카드보다 큰 카드 중에서 가장 작은 카드를 냄, 한번 내면 다시 못냄) -> 민수가 카드를 내지 못하는 경우는 없다 가정

        // 우선 민수가 쓸 카드 정렬을 하고
        StringBuilder answer = new StringBuilder();
        Arrays.sort(card);
        // visited배열 관리
        boolean[] used = new boolean[m];
        // 우선 철수가 낼 카드 순회
        for (int i = 0; i < k; i++) {
            int picked = chulsu[i];
            // 낼 카드 인덱스는 어퍼바운드고 만약에 어퍼 바운드가 visited면 visted아닐떄까지 인덱스 ++
            int idx = binarySearch(used, picked);
            while (used[idx]) idx++;
            answer.append(card[idx]).append("\n");
            used[idx] = true;
        }
        return answer.toString();
    }

    private static int binarySearch(boolean[] used, int target) {
        int left = 0;
        int right = m;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (card[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}