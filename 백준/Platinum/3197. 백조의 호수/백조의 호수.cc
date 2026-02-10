#include <bits/stdc++.h>
using namespace std;
int n, m;
char mp[1501][1501];
bool visited[1501][1501];
bool sv[1501][1501];
const int dir[4][2] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; 
queue<pair<int, int>> ice;
int sw;
int swan[2][2];
int day;
bool in(int r, int c) {
    return 0 <= r && r < n && 0 <= c && c < m;
}
bool melt (int r, int c) {
    for (auto d : dir) {
        int nr = r + d[0];
        int nc = c + d[1];
        if (in(nr, nc) && (mp[nr][nc] == '.' || mp[nr][nc] == 'L')) {
            return true;
        }
    }

    return false;
}
int main() {
    cin >> n >> m;
    for (int i = 0; i < n; i++){
        string s;
        cin >> s;
        for (int j = 0; j < m; j++){
            mp[i][j] = s[j];
        }
    }
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (mp[i][j] == 'L') {
                swan[sw][0] = i;
                swan[sw][1] = j;
                sw++;
                mp[i][j] = '.';
            }

            if (mp[i][j] == 'X' && melt(i, j)) {
                visited[i][j] = 1;
                ice.push({i, j});
            }
        }
        
    }
    
    // 얼음인데 물에 닿아있는 얼음을 큐에 넣어줘 (사방중 하나가 .)
    // 녹여 -> bfs하면서 0으로 바꿔주고 얼음쪽으로 전진, visited 체크, 레벨별로 돌려야하는거 같은데
    // 백조가 서로 이동 가능한지 체크

    // 이제 녹아야하는 얼음이 있음
        queue<pair<int, int>> ss;
        ss.push({swan[0][0], swan[0][1]});
        sv[swan[0][0]][swan[0][1]] = true;
        bool found = false;
    while (true) {
        queue<pair<int, int>> temp;
        while (ss.size()) {
            auto it = ss.front(); ss.pop();
            int y = it.first;
            int x = it.second;
            if (y == swan[1][0] && x == swan[1][1]) {
                found = true;
                break;
            }
            for (auto d : dir) {
                int ny = y + d[0];
                int nx = x + d[1];
                if (in(ny, nx) && !sv[ny][nx] && mp[ny][nx] == '.') {
                    sv[ny][nx] = true;
                    ss.push({ny, nx});
                } else if (in(ny,nx) && !sv[ny][nx] && mp[ny][nx] == 'X') {
                    sv[ny][nx] = true;
                    temp.push({ny, nx});
                }
            }
        }
        ss = temp;

        if (found) {
            break;
        }

        day++;
        int len = ice.size();
        // 레벨별로 돌리자
        for (int i = 0; i < len; i++) {
            auto cur = ice.front(); ice.pop();
            int cr = cur.first;
            int cc = cur.second;
            mp[cr][cc] = '.';
            for (auto d : dir) {
                int nr = cr + d[0];
                int nc = cc + d[1];
                if (in(nr, nc) && !visited[nr][nc] && mp[nr][nc] == 'X') {
                    visited[nr][nc] = true;
                    ice.push({nr, nc});
                }
            }
        }
    }

    cout << day << '\n';
    return 0;
}