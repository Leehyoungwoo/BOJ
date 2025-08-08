class Solution {
    
    private int n, m;
    
    private final int[][] direction = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    
    public int solution(String[] storage, String[] requests) {
        n = storage.length;
        m = storage[0].length();
        // 하나만 들어오면 사이드에서 시작해서 bfs로 가능한 모든 알파벳을 제거하는거고
        // 두개가 들어오면 모든 알파벳 제거네
        boolean[][] isRemoved = new boolean[n][m];
        
        for (int i = 0; i < requests.length; i++) {
            String req = requests[i];
            if (req.length() == 2) {
                char target = req.charAt(0);
                for (int r = 0; r < n; r++) {
                    for (int c = 0; c < m; c++) {
                        if (isRemoved[r][c]) {
                            continue;
                        }
                        if (storage[r].charAt(c) == target) {
                            isRemoved[r][c] = true;
                        }
                    }
                }
            } else {
                char target = req.charAt(0);
                // 아니 사이드에서 시작하지말고 
                // target 알파벳을 찾아서
                // 거기서 사이드로 나갈 수 있는지를 찾는게 낫지 않나?
                // 그게 removed로 연결되어서 사이드까지 빠져나간다? 그러면 꺼내기 가능이잖아
                boolean[][] marked = new boolean[n][m];
                for (int r = 0; r < n; r++) {
                    for (int c = 0; c < m; c++) {
                        if (isRemoved[r][c]) {
                            continue;
                        }
                        if (storage[r].charAt(c) == target&& isPathToSide(storage, r, c, isRemoved, target)) {
                            marked[r][c] = true;
                        }
                    }
                }
                // mark를 isRemoved에 기록
                for (int r = 0; r < n; r++) {
                    for (int c = 0; c < m; c++){
                        if (marked[r][c]) {
                            isRemoved[r][c] = true;
                        }
                    }
                }
            }
        }
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!isRemoved[i][j]) answer++;
            }
        }
        return answer;
    }
    
    private boolean isPathToSide(String[] storage, int r, int c, boolean[][] isRemoved, char target) {
        boolean[][] visited = new boolean[n][m];
        return dfs(r, c, isRemoved, visited);
    }

    private boolean dfs(int r, int c, boolean[][] isRemoved, boolean[][] visited) {
        if (r == 0 || r == n - 1 || c == 0 || c == m - 1) {
            return true;
        }

        visited[r][c] = true;

        for (int[] dir : direction) {
            int nextR = r + dir[0];
            int nextC = c + dir[1];

            if (!isInRange(nextR, nextC)) continue;
            if (!isRemoved[nextR][nextC]) continue;
            if (visited[nextR][nextC]) continue;

            if (dfs(nextR, nextC, isRemoved, visited)) {
                return true;
            }
        }

        return false;
    }
    
    private boolean isInRange(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < m;
    }
}