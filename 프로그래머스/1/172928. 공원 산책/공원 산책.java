class Solution {
    
    private int curR, curC;
    // 동 서 남 북 순이야
    private final int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private int n, m;
    
    public int[] solution(String[] park, String[] routes) {
        n = park.length;
        m = park[0].length();
        // 구현문제
        // 루트 배열을 돌면서 이동을 할거고 S에서 시작할거야
        // curR curC로 시작하면서 이동하면 될거고
        // 명령 수행전에 map밖으로 나가는지, 직선으로 이동할 때 X에 걸리는지 확인할거야
        // 그리고 마지막에 curR, curC를 배열형태로 리턴하면 돼
        // 우선 curR, curC의 시작점을 찾아보자
        for (int i = 0; i < park.length; i++) {
            for (int j = 0; j < park[i].length(); j++) {
                char c = park[i].charAt(j);
                if (c == 'S') {
                    curR = i;
                    curC = j;
                }
            } 
        }
        // 그리고나서 이제 루트를 이동해보자
        for (int i = 0; i < routes.length; i++) {
            String[] route = routes[i].split(" ");
            int dir = findDir(route[0]);
            // 만약에 범위 밖이면 무시
            if (!isInRange(curR, curC, route[1], dir)) {
                continue;
            }
            // 장애물 만남 여부
            boolean flag = false;
            // 이동하다 장애물을 만나면 무시
            int nextR = curR;
            int nextC = curC;
            for (int j = 0; j < Integer.parseInt(route[1]); j++) {
                nextR += direction[dir][0];
                nextC += direction[dir][1];
                if (park[nextR].charAt(nextC) == 'X') {
                    flag = true;
                    break;
                }
            }
            
            if (flag) {
                continue;
            }
            curR = nextR;
            curC = nextC;
        }
        
        return new int[] {curR, curC};
    }
    
    private int findDir(String route) {
        if (route.equals("E")) {
            return 0;
        }
        if (route.equals("W")) {
            return 1;
        }
        if (route.equals("S")) {
            return 2;
        }
        
        return 3;
    }
    
    private boolean isInRange(int r, int c, String route, int dir) {
        int nextR = r + direction[dir][0] * Integer.parseInt(route);
        int nextC = c + direction[dir][1] * Integer.parseInt(route);
        return 0 <= nextR && nextR < n && 0 <= nextC && nextC < m;
    }
}