#include <bits/stdc++.h>
using namespace std;
int g[101][101];
bool visited[101][101];
int n, m;
const int dir[4][4] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
bool isInRange(int r, int c) {
    return 0 <= r && r < n && 0 <= c && c < m;
}
int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> n >> m;

    for (int i = 0; i < n; i++) {
        string s;
        cin >> s;
        for (int j = 0; j < m; j++) {
            g[i][j] = s[j] - '0';
        }
    }

    queue<tuple<int, int, int>> q;
    visited[0][0] = true;
    q.push({0, 0, 1});

    while (!q.empty()) {
        auto cur = q.front();
        q.pop();
        int cr = get<0>(cur);
        int cc = get<1>(cur);;
        int cnt = get<2>(cur);
        if (cr == n - 1 && cc == m - 1) {
            cout << cnt << "\n";
            break;
        }
        for (auto d : dir) {
            int nr = cr + d[0];
            int nc = cc + d[1];
            if (isInRange(nr, nc) && g[nr][nc] && !visited[nr][nc]) {
                visited[nr][nc] = true;
                q.push({nr, nc, cnt + 1});
            }
        }
    }

    return 0;
}