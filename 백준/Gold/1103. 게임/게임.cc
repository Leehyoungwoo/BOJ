#include <bits/stdc++.h>
using namespace std;
int n, m;
string s;
char mp[51][51];
int dp[51][51];
int ret;
const int dr[4] = {1, -1, 0, 0};
const int dc[4] = {0, 0, -1, 1};
bool visited[51][51];
bool in(int r, int c) {
    return 0 <= r && r < n && 0 <= c && c < m && isdigit(mp[r][c]);
}
int go(int r, int c) {
    int &ret = dp[r][c];
    if (ret != -1) return ret;
    ret = 1;
    int cur = mp[r][c] - '0';
    for (int i = 0; i < 4; i++) {
        int nr = r + dr[i] *  cur;
        int nc = c + dc[i] * cur;

        if (!in(nr, nc)) continue;
        if (visited[nr][nc]) return 1e9;

        visited[nr][nc] = true;
        ret = max(ret, go(nr, nc) + 1);
        visited[nr][nc] = false;
    }

    return ret;
}
int main() {
    cin >> n >> m;
    for (int i = 0; i < n; i++) {
        cin >> s;
        for (int j = 0; j < m; j++) {
            mp[i][j] = s[j];
        }
    }
    memset(dp, -1, sizeof(dp));
    visited[0][0] = true;
    int ret =  go(0, 0);
    if (ret >= 1e9) cout << -1 << '\n';
    else cout << ret << '\n';
    return 0;
}