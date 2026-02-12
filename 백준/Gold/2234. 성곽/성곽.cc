#include <bits/stdc++.h>
using namespace std;
int n, m;
int wall[51][51];
int mp[51][51];
// 서 북 동 남
int cnt;
int max_area;
// 없앴을 때 max
int mx;
int visited[51][51];
int sz[3000];
const int dir[4][2] = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
vector<int> adj[3000];
bool in(int r, int c) {
    return 0 <= r && r < m && 0 <= c && c < n;
}

void makeadj(int r, int c) {
    queue<pair<int, int>> q;
    q.push({r, c});
    int value = mp[r][c];
    visited[r][c] = 1;
    set<int> s;
    while(q.size()) {
        auto cur = q.front(); q.pop();
        int cr = cur.first;
        int cc = cur.second;
        for (auto d : dir) {
            int nr = cr + d[0];
            int nc = cc + d[1];
            if (!in(nr, nc)) continue;
            if (visited[nr][nc]) continue;
            if (mp[nr][nc] == value) {
                q.push({nr, nc});
                visited[nr][nc] = visited[cr][cc] + 1;
            } else {
                s.insert(mp[nr][nc]);
            }
        }
    }

    for (auto i : s) {
        adj[value].push_back(i);
    }
}
int bfs(int r, int c) {
    int a = 0;
    queue<pair<int, int>> q;
    q.push({r, c});
    visited[r][c] = 1;
    while (q.size()) {
        auto cur = q.front();
        q.pop();
        int curR = cur.first;
        int curC = cur.second;
        mp[curR][curC] = cnt;
        a++;
        for (int i = 0; i < 4; i++){
            // wall이 존재하는 영역, 비트가 켜저있는 영역은 못감
            if (wall[curR][curC] & (1 << i)) continue;
            int nr = curR + dir[i][0];
            int nc = curC + dir[i][1];
            if ((in(nr, nc)) && !visited[nr][nc]) {
                visited[nr][nc] = visited[curR][curC] + 1;
                q.push({nr, nc});
            }
        }
    }
    return a;
}
int main(){
    cin >> n >> m;
    for (int i = 0; i < m; i++){
        for (int j = 0; j < n; j++){
            cin >> wall[i][j];
        }
    }

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++){
            if (!visited[i][j]) {
                int area = bfs(i, j);
                sz[cnt] = area;
                cnt++;
                max_area = max(max_area, area);
            }
        }
    }
    // 벽중에 하나를 골라서 그거 한개 고르기 아닌가?
    // 벽을 하나 없애면 옆방이랑 합쳐짐
    // 그러면 라벨링 된 방이 있어야하고 그 방별로 인접한 방의 넓이를 구해서 그 방과 합의 조합을 구하면 되는거 아닌가?
    memset(visited, 0, sizeof(visited));
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (visited[i][j]) continue;
            makeadj(i, j);
        }
    }
    for (int i = 0; i <= cnt; i++) {
        for (auto it : adj[i]) {
            mx = max(mx, sz[i] + sz[it]);
        }
    }
    cout << cnt << '\n';
    cout << max_area << '\n';
    cout << mx << '\n';
    return 0;
}