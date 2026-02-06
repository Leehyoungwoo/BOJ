#include <bits/stdc++.h>
using namespace std;
// ## 안전한 영역의 최댓값
// 2 <= n <= 100
// 1<=높이<=100
// 비가 오는 양은 최소 <= 최대 - 1로 조사 => 최대로 되면 모두 잠겨서 의미 없으니까?
const int dir[4][2] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
int n;
int m[101][101];
bool visited[101][101];
int mx_a = INT_MIN;
bool isInRange(int r, int c){
    return 0 <= r && r < n && 0 <= c && c < n;
}
void bfs(int i, int j, int r) {
    queue<pair<int, int>> q;
    q.push({i, j});
    visited[i][j] = true;

    while (q.size()) {
        auto cur = q.front();
        q.pop();
        int cr = cur.first;
        int cc = cur.second;
        for (auto d : dir) {
            int nr = cr + d[0];
            int nc = cc + d[1];
            if (isInRange(nr, nc) && !visited[nr][nc] && m[nr][nc] > r) {
                q.push({nr, nc});
                visited[nr][nc] = true;
            }
        }
    }
}
int main() {
    cin >> n;
    int mi = INT_MAX;
    int ma = INT_MIN;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++){
            cin >> m[i][j];
            mi = min(mi, m[i][j]);
            ma = max(ma, m[i][j]);
        }
    }

    // 비가 오는 양 => 최솟값부터 최댓값 -1 => 최댓값만큼 오면 모든게 잠기니까 의미가 없어서
    for (int r = 0; r <= ma - 1; r++) {
        int cnt = 0;
        memset(visited, 0, sizeof(visited));

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && m[i][j] > r) {
                    bfs(i, j, r);
                    cnt++;
                }
            }
        }
        mx_a = max(mx_a, cnt);
    }

    cout << mx_a;
    return 0;
}