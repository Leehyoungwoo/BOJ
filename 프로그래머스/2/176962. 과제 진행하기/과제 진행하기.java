import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        List<String> answer = new ArrayList<>();

        // 시간 순 정렬
        Arrays.sort(plans, Comparator.comparingInt(p -> timeToMin(p[1])));

        Stack<Task> stack = new Stack<>();

        for (int i = 0; i < plans.length - 1; i++) {
            String name = plans[i][0];
            int startTime = timeToMin(plans[i][1]);
            int playTime = Integer.parseInt(plans[i][2]);

            int nextStartTime = timeToMin(plans[i + 1][1]);
            int remainTime = nextStartTime - startTime;

            if (remainTime >= playTime) {
                // 이번 과제 끝낼 수 있음
                answer.add(name);
                remainTime -= playTime;

                // 남는 시간 동안 스택에 있던 멈춘 과제 이어서 처리
                while (!stack.isEmpty() && remainTime > 0) {
                    Task paused = stack.pop();
                    if (remainTime >= paused.playTime) {
                        remainTime -= paused.playTime;
                        answer.add(paused.name);
                    } else {
                        paused.playTime -= remainTime;
                        stack.push(paused);
                        break;
                    }
                }
            } else {
                // 못 끝내니까 멈춰야 함
                stack.push(new Task(name, playTime - remainTime));
            }
        }

        // 마지막 과제는 무조건 완료
        answer.add(plans[plans.length - 1][0]);

        // 멈췄던 과제 마저 처리
        while (!stack.isEmpty()) {
            answer.add(stack.pop().name);
        }

        return answer.toArray(new String[0]);
    }

    private int timeToMin(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }

    static class Task {
        String name;
        int playTime;

        Task(String name, int playTime) {
            this.name = name;
            this.playTime = playTime;
        }
    }
}
