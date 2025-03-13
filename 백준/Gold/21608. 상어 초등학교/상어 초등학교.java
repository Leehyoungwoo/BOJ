import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int n;
    private static int students;
    private static Map<Integer, List<Integer>> likes;
    private static List<Integer> studentsList;
    private static int[][] matrix;
    private static final int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static int likeSum = 0;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
        System.out.println(likeSum);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        studentsList = new ArrayList<>();
        matrix = new int[n][n];
        students = (int) Math.pow(n, 2);
        likes = new HashMap<>();

        for (int i = 0; i < students; i++) {
            String[] s = input.readLine().split(" ");
            int member = Integer.parseInt(s[0]);
            studentsList.add(member);
            likes.put(member, new ArrayList<>());
            for (int j = 1; j <= 4; j++) {
                likes.get(member).add(Integer.parseInt(s[j]));
            }
        }
    }

    private static void findAnswer() {
        likeSum = 0;  // ✅ 만족도 계산을 위한 초기화

        for (int student : studentsList) {
            List<int[]> candidateSeats = new ArrayList<>();
            int maxLikeCount = -1;

            // ✅ 1. "좋아하는 학생이 인접한 칸이 가장 많은 칸" 찾기
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] != 0) continue; // ❗ 이미 자리가 차 있는 경우 제외

                    int likeCount = 0;
                    int emptyCount = 0;

                    for (int[] d : directions) {
                        int nextR = i + d[0];
                        int nextC = j + d[1];
                        if (!isInRange(nextR, nextC)) continue;

                        if (likes.get(student).contains(matrix[nextR][nextC])) {
                            likeCount++;
                        }
                        if (matrix[nextR][nextC] == 0) {
                            emptyCount++;
                        }
                    }

                    if (likeCount > maxLikeCount) {
                        maxLikeCount = likeCount;
                        candidateSeats.clear();
                        candidateSeats.add(new int[]{i, j, emptyCount});
                    } else if (likeCount == maxLikeCount) {
                        candidateSeats.add(new int[]{i, j, emptyCount});
                    }
                }
            }

            // ✅ 2. "비어있는 칸이 가장 많은 칸" 찾기
            int maxEmptyCount = -1;
            List<int[]> finalSeats = new ArrayList<>();

            for (int[] seat : candidateSeats) {
                if (seat[2] > maxEmptyCount) {
                    maxEmptyCount = seat[2];
                    finalSeats.clear();
                    finalSeats.add(seat);
                } else if (seat[2] == maxEmptyCount) {
                    finalSeats.add(seat);
                }
            }

            // ✅ 3. "행이 작은 순 → 열이 작은 순" 정렬 후 선택
            finalSeats.sort(Comparator.comparingInt(a -> a[0] * n + a[1]));
            int[] chosenSeat = finalSeats.get(0);
            matrix[chosenSeat[0]][chosenSeat[1]] = student;
        }

        // ✅ 만족도 계산
        calculateSatisfaction();
    }

    private static void calculateSatisfaction() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int student = matrix[i][j];
                int count = 0;

                for (int[] d : directions) {
                    int nextR = i + d[0];
                    int nextC = j + d[1];
                    if (!isInRange(nextR, nextC)) continue;

                    if (likes.get(student).contains(matrix[nextR][nextC])) {
                        count++;
                    }
                }

                // ✅ 만족도 점수 적용
                likeSum += (count == 0) ? 0 :
                        (count == 1) ? 1 :
                                (count == 2) ? 10 :
                                        (count == 3) ? 100 : 1000;
            }
        }
    }

    private static boolean isInRange(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < n;
    }
}