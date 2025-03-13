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
    private static int likeSum;

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
            String line = input.readLine();
            String[] s = line.split(" ");
            int member = Integer.parseInt(s[0]);
            studentsList.add(member);
            likes.put(member, new ArrayList<>());
            for (int j = 1; j < s.length; j++) {
                likes.get(member).add(Integer.parseInt(s[j]));
            }
        }
    }

    private static void findAnswer() {
        likeSum = 0;
        // 학생 리스트 순회하며
        for (int student : studentsList) {
            int maxLikeCount = 0;
            List<int[]> cadidates = new ArrayList<>();
            // matrix의 모든 행과 열에서
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int likeCount = 0;
                    int emptyCount = 0;
                    if (matrix[i][j] != 0) continue;
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
                        cadidates.clear();
                        cadidates.add(new int[] {i, j, emptyCount});
                    } else if (likeCount == maxLikeCount) {
                        cadidates.add(new int[] {i, j, emptyCount});
                    }
                }
            }
            // 좋아하는 친구 후보지 1명이면 아래로 갈 필요 X
            if (cadidates.size() == 1) {
                matrix[cadidates.get(0)[0]][cadidates.get(0)[1]] = student;
                continue;
            }
            // 한군데가 아니면, 빈칸이 많은 곳으로 갈거야
            // 행렬이 가장 낮은거니가 get(0) 맞지 않나?
            int maxEmptyCount = 0;
            List<int[]> finalList = new ArrayList<>();

            for(int[] seat : cadidates) {
                if (seat[2] > maxEmptyCount) {
                    maxEmptyCount = seat[2];
                    finalList.clear();
                    finalList.add(seat);
                } else if (seat[2] == maxEmptyCount) {
                    finalList.add(seat);
                }
            }
            finalList.sort(Comparator.comparingInt(a -> a[0] * n + a[1]));
            int[] chose = finalList.get(0);
            matrix[chose[0]][chose[1]] = student;
        }

        // 만족도 조사
        // matrix 순회하면서 각 학생이 좋아하는 학생 수 찾기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int student = matrix[i][j];
                int count = 0;
                for (int[] d : directions) {
                    int nextR = i + d[0];
                    int nextC = j + d[1];
                    if (!isInRange(nextR, nextC)) {
                        continue;
                    }
                    if (likes.get(student).contains(matrix[nextR][nextC])) {
                        count++;
                    }
                }
                if (count == 0) {
                    likeSum += 0;
                } else if (count == 1) {
                    likeSum++;
                } else if (count == 2) {
                    likeSum += 10;
                } else if (count == 3) {
                    likeSum += 100;
                } else if (count == 4) {
                    likeSum += 1000;
                }
            }
        }
    }

    private static boolean isInRange(int r, int c) {
        return 0 <= r && r < n && c >= 0 && c < n;
    }
}