#include <bits/stdc++.h>
using namespace std;
int n, m;
int mp[10][10];
int mn = 64;
vector<pair<int, int>> v;
map<int, vector<vector<int>>> dir;
bool visited[9][9];
int comb[9];
const int d[4][2] = {{1, 0}, {0, 1}, {-1, 0}, {0 , -1}};
void settingDir() {
    // 한뱡향
    dir[1] = {{0}, {1}, {2}, {3}};
    // 대칭
    dir[2] = {{0, 2}, {1, 3}};
    // ㄱ자
    dir[3] = {{0, 1}, {1, 2}, {2, 3}, {0, 3}};
    //3방향
    dir[4] = {{0, 1, 2}, {1, 2, 3}, {0, 2, 3}, {0, 1, 3}};
    dir[5] = {{0, 1, 2, 3}};
}
bool in(int r, int c) {
    return 0 <= r && r < n && 0 <= c && c < m;
}
void see(pair<int, int> &camera, int idx) {
    int r = camera.first;
    int c= camera.second;
    int type = mp[r][c];
    for (int i = 0; i < (int) dir[type][idx].size(); i++) {
        int k = dir[type][idx][i];
        int cr = r;
        int cc = c;
        while (true) {
            visited[cr][cc] = true;
            int nr = cr + d[k][0];
            int nc = cc + d[k][1];
            if (!in(nr, nc)) break;
            if (mp[nr][nc] == 6) break;
            cr = nr;
            cc = nc;
        }
    }
}
void go(int idx) {
    if (idx == (int) v.size()) {
        memset(visited, false, sizeof(visited));
        for (int i = 0; i < v.size(); i++) {
            auto it = v[i];
            int idx = comb[i];
            see(v[i], idx);
        }
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mp[i][j] != 6 && !visited[i][j]) {
                    cnt++;
                }
            }
        }
        mn = min(mn, cnt);
        return;
    }
    auto cur = v[idx];
    int type = mp[cur.first][cur.second];
    for (int i = 0; i < (int)dir[type].size(); i++) {
        comb[idx] = i;
        go(idx + 1);
    }
}
int main(){
    cin >> n >> m;
    for (int i = 0; i < n ; i++) {
        for (int j = 0; j < m; j++) {
            cin >> mp[i][j];
            if (mp[i][j] > 0 && mp[i][j] < 6) {
                v.push_back({i, j});
            }
        }
    }
    // 세팅
    settingDir();
    go(0);

    cout << mn << '\n';
    return 0;
}