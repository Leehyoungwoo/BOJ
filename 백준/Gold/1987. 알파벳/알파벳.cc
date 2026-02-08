#include <bits/stdc++.h>
using namespace std;
int n, m;
char mp[20][20];
bool visited[20][20];
int a[26];
const int dir[4][2] ={{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
bool in(int r, int c) {
    return 0<=r&&r <n&&0<=c && c< m;
}
int dfs(int r, int c) {
    visited[r][c] = true;
    // cout<< r << " : " << c << " : " << mp[r][c] << "\n";
    a[mp[r][c] - 'A']++;
    int ret = 1;
    for (auto d : dir) {
        int nr = r + d[0];
        int nc = c + d[1];
        if (!in(nr, nc)) continue;
        if (visited[nr][nc]) continue;
        if (a[mp[nr][nc] - 'A'] != 0) continue;
        ret = max(ret, 1 + dfs(nr, nc));
    }
    visited[r][c] = false;
    a[mp[r][c] - 'A']--;
    return ret;
}
int main() {
    cin >> n >> m;
    for (int i = 0; i < n; i++) {
        string s;
        cin >> s;
        for (int j = 0; j < m; j++){
            mp[i][j] = s[j];
        }
    }   
    cout << dfs(0, 0) << "\n";
}