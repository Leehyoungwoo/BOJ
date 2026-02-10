#include <bits/stdc++.h>
using namespace std;
int n, m;
char mp[20][20];
int mask;
const int dir[4][2] = {{1, 0}, {-1 ,0}, {0, 1}, {0, -1}};
bool in(int r, int c) {
    return 0 <= r && r < n && 0 <= c && c < m;
}
int dfs(int r, int c, int mask) {
    int ret = 1;
    for (auto d : dir) {
        int nr = r + d[0];
        int nc = c + d[1];
        if (!in(nr, nc)) continue;
        int next = mp[nr][nc] - 'A';
        if (mask & (1 << next)) continue;
        ret = max(ret, 1 + dfs(nr, nc, mask | (1 << next)));
    }

    return ret;
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
    int start = mp[0][0] - 'A';
    cout << dfs(0, 0, mask |=(1 << start)) << '\n';

    return 0;
}