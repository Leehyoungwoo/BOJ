#include <bits/stdc++.h>
using namespace std;
int n, m, k;
char mp[6][6];
bool visited[6][6];
const int dir[4][2] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
bool in(int r, int c) {
    return 0<=r && r < n && 0 <= c && c < m;
}
int dfs(int cr, int cc, int dis) {
    if (cr == 0 && cc == m - 1 && dis == k){
        return 1;
    }
    int ret = 0;
    for (auto d : dir) {
        int nr = cr + d[0];
        int nc = cc + d[1];
        if (in(nr, nc) && !visited[nr][nc] && mp[nr][nc] != 'T') {
            visited[nr][nc] = true;
            ret+=dfs(nr, nc, dis + 1);
            visited[nr][nc] = false;
        }
    }

    return ret;
}
int main() {
    cin >> n >> m >> k;
    for (int i = 0; i < n; i++) {
        string s;
        cin >> s;
        for (int j = 0; j < m; j++){
            mp[i][j] = s[j];
        }
    }
    visited[n - 1][0] = true;
    cout << dfs(n - 1, 0, 1) << "\n";
    return 0;
}