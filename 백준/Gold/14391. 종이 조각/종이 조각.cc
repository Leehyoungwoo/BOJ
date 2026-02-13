#include <bits/stdc++.h>
using namespace std;
int n, m;
int mp[5][5];
int mx = INT_MIN;
bool visited[5][5];
const int dir[2][2] = {{0, 1}, {1, 0}};
string temp;
bool in(int r, int c) {
    return 0 <= r && r < n && 0 <= c && c < m;
}
int go (int r, int c, int mask) {
    int sum = 0;
    queue<pair<int, int>> q;
    q.push({r, c});
    int idx = r * m + c;
    int d = (mask >> idx) & 1;
    visited[r][c] = true;
    while (q.size()) {
        auto cur = q.front(); q.pop();
        int cr = cur.first;
        int cc = cur.second;

        sum *= 10;
        sum+=mp[cr][cc];

        int nr = cr + dir[d][0];
        int nc = cc + dir[d][1];
        if (!in(nr, nc)) continue;
        if (visited[nr][nc]) continue;
        int nd = ((mask & (1 << (nr * m + nc))) != 0); 
        if (d == nd) {
            q.push({nr, nc});
            visited[nr][nc] = true;
        }
    }

    return sum;
}
int main(){
    cin >> n >> m;
    for (int i = 0; i < n; i++) {
        cin >> temp;
        for (int j = 0; j < m; j++) {
            mp[i][j] = temp[j] - '0';
        }   
    }

    for (int mask = 0; mask <= (1 << n * m) - 1; mask++) {
        int sum = 0;
        memset(visited, false, sizeof(visited));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j]) {
                    int num = go(i, j, mask);
                    sum+=num;
                }
            }
        }
        mx = max(mx, sum);
    }

    cout << mx << '\n';

    return 0;
}