#include <bits/stdc++.h>
using namespace std;
int n, m;
string input;
const int dir[4][2] = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
char mp[51][51];
int dp[51][51];
bool visited[51][51];
bool flag;
bool in(int r, int c) {
    return 0 <= r && r < n && 0 <= c && c < m;
}
int go(int r, int c) {
    int & ret = dp[r][c];
    if (ret != -1) return dp[r][c];
    ret = 0;
    for (auto d : dir) {
        int nr = r + d[0] * (mp[r][c] - '0');
        int nc = c + d[1] * (mp[r][c] - '0');
        if (!in(nr, nc) || mp[nr][nc] == 'H') continue;
        if (visited[nr][nc]) {
            flag = true;
            return 0;
        }
        visited[nr][nc] = true;
        ret = max(ret, go(nr, nc) + 1);
        visited[nr][nc] = false;
    }

    return ret; 
}
int main() {
    cin >> n >> m;
    for (int i = 0; i < n; i++) {
        cin >> input;
        for (int j = 0; j < m; j++) {
            mp[i][j] = input[j];
        }
    }

    memset(dp, -1, sizeof(dp));
    visited[0][0] = true;
    int ret = go(0, 0);
    if (flag) cout << -1 << '\n';
    else cout << ret + 1 << '\n';
    return 0;
}