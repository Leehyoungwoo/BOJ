#include <bits/stdc++.h>
using namespace std;
int n, m;
char mp[300][300];
bool visited[300][300];
int x, y, x2, y2;
int cnt;
const int dir[4][2] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
bool in(int r, int c) {
    return 0 <=r && r < n && 0 <= c && c < m;
}
// 주난이 *, 초코바 #
// 한번의 점프는 한겹의 친구를 쓰러트림 => 벽부수기
int main() {
    cin >> n >> m;
    cin >> x >> y >> x2 >> y2;
    for (int i = 0; i < n; i++) {
        string s;
        cin >> s;
        for (int j = 0; j < m; j++) {
            mp[i][j] = s[j];
        }
    }
    // 최소 점프니까 bfs
    
    queue<pair<int, int>> q;
    // 초코바 찾을떄까지 점프
    bool found = false;
    while (true) {
        if (found) {
            break;
        }
        // 찾아보기
        cnt++;
        memset(visited, 0, sizeof(visited));
        q.push({x - 1, y - 1});
        visited[x - 1][y - 1] = true;
        while (q.size()) {
            auto cur = q.front();
            q.pop();
            int cr = cur.first;
            int cc = cur.second;
            if (mp[cr][cc] == '1') {
                mp[cr][cc] = 0;
                continue;
            }
            if (mp[cr][cc] == '#') {
                found = true;
                break;
            }
            for (auto d : dir) {
                int nr = cr + d[0];
                int nc = cc + d[1];
                if (in(nr, nc) && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.push({nr, nc});
                }
            }
        }
    }

    cout << cnt << "\n";
    return 0;
}