#include <bits/stdc++.h>
using namespace std;
/// ## 연결된 컴포넌트의 수를 구하는 문제
int t;
int cnt;
int m, n, k;
const int dir[4][2] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
int grid[51][51];
bool visited[51][51];
bool isInRange(int r, int c) {
    return 0 <= r && r < n && 0 <= c && c < m;
}
void bfs(int i, int j) {
    queue<pair<int, int>> q;
    visited[i][j] = true;
    q.push({i, j});

    while (q.size()) {
        auto cur = q.front();
        q.pop();
        int curR = cur.first;
        int curC = cur.second;
        for (auto d : dir) {
            int nr = curR + d[0];
            int nc = curC + d[1];
            if (isInRange(nr, nc) && !visited[nr][nc] && grid[nr][nc]) {
                q.push({nr, nc});
                visited[nr][nc] = true;
            }
        }
    }
} 
int main() {
    cin >> t;
    while (t--) {
        cnt = 0;
        memset(grid, 0, sizeof(grid));
        memset(visited, 0, sizeof(visited));
        cin >> m >> n >> k;
        for (int i = 0; i < k; i++){
            int c, r;
            cin >> c >> r;
            grid[r][c] = 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && grid[i][j]) {
                    cnt++;
                    bfs(i, j);
                }
            }
        }

        cout << cnt << "\n";
    }
    
    return 0;
}