#include <bits/stdc++.h>
using namespace std;
int n, m;
int mp[101][101];
bool visited[101][101];
int ret;
int prv;
int cnt;
const int dir[4][2] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
bool isInRange(int r, int c) {
    return 0<= r && r < n && 0 <= c && c < m;
}
void print() {
    cout << "------------------------" << "\n";
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++){
            cout << mp[i][j] << " ";
        }
        cout << "\n";
    }
}
bool touch_side(int i, int j) {
    queue<pair<int, int>> que;
    que.push({i, j});
    visited[i][j] = true;
    while (que.size()) {
        auto cur = que.front();
        que.pop();
        int cr = cur.first;
        int cc = cur.second;
        if (cr == 0 || cr == n - 1 || cc == 0 || cc == m - 1) {
            return true;
        }
        for (auto d : dir) {
            int nr = cr + d[0];
            int nc = cc + d[1];
            if (isInRange(nr, nc) && mp[nr][nc] == 0 && !visited[nr][nc]) {
                que.push({nr, nc});
                visited[nr][nc] = true;
            }
        }
    }

    return false;
}
void put_in_que(queue<pair<int, int>> &q) {
    
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++){
            memset(visited, 0, sizeof(visited));
            if (mp[i][j] == 1 && touch_side(i, j)) {
                q.push({i, j});
            }
        }
    }
}
int main(){
    // 공기와 닿으면 치즈는 녹음
    // 치즈 구멍은 안녹음 (치즈로 둘러쌓인 곳)
    // 치즈 구멍이 밖이랑 연결되면 녹음
    // 전부다 녹아 없어지는 시간은?
    cin >> n >> m;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> mp[i][j];
            if (mp[i][j] == 1) {
                cnt++;
            }
        }
    }
    queue<pair<int, int>> q;

    while (cnt > 0) {
        ret++;
        // 테두리 치즈를 담자
        put_in_que(q);
        prv = cnt;
        // cout << (int) q.size() << "\n";
        cnt -= (int) q.size();
        while (q.size()) {
            auto c = q.front();
            q.pop();
            mp[c.first][c.second] = 0;
        }
        // print();
    }

    cout << ret << "\n";
    cout << prv << "\n";
    return 0;
}