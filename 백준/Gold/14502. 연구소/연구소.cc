#include <bits/stdc++.h>
using namespace std;
int n, m;
int mp[10][10];
bool visited[10][10];
int mx = INT_MIN;
vector<int> p;
const int dir[4][2] = {{1,0},{-1,0},{0,1},{0, -1}};
vector<int> v;
int bfs() {
    memset(visited, 0, sizeof(visited));
    queue<pair<int, int>> q;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (mp[i][j] == 2) {
                q.push({i, j});
                visited[i][j] = true;
            }
        }
    }

    while (q.size()) {
        auto cur = q.front();
        q.pop();
        int cr = cur.first;
        int cc = cur.second;
        for (auto d : dir){
            int nr = cr + d[0];
            int nc = cc + d[1];
            if (0 <= nr && nr < n && 0<=nc && nc < m && !visited[nr][nc] && mp[nr][nc] == 0) {
                q.push({nr, nc});
                visited[nr][nc] = true;
            }
        }
    }
    int cnt = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (mp[i][j] == 0 && !visited[i][j]) cnt++;
        }
    }

    return cnt;
}
void comb(int start, vector<int> v) {
    if (v.size() == 3) {
        for (int i = 0; i < 3; i++) {
            int r = p[v[i]] / m;
            int c = p[v[i]] % m;
            mp[r][c] = 1;
        }
        int area = bfs();
        mx = max(mx, area);
        for (int i = 0; i < 3; i++) {
            int r = p[v[i]] / m;
            int c = p[v[i]] % m;
            mp[r][c] = 0;
        }
        return;
    } 
    for (int i = start; i < p.size(); i++) {
        v.push_back(i);
        comb(i + 1, v);
        v.pop_back();
    }
}
int main() {
    cin >> n >> m;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> mp[i][j];
            if (mp[i][j] == 0) {
                p.push_back(i * m + j);
            }
        }
    }
    comb(0, v);

    cout << mx << "\n";

    return 0;
}