#include <bits/stdc++.h>
using namespace std;
int m, n, k;
int mp[101][101];
int lx, ly, rx, ry;
bool visited[101][101];
string result;
vector<int> area;
const int dir[4][2] = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
// ## 색칠하고 남은 영역의 개수 + 넓이 구하기
// 연결된 컴포넌트
bool isInRange(int r, int c) {
    return 0 <= r && r < m && 0 <= c && c < n;
}
int bfs(int i, int j) {
    queue<pair<int, int>> q;
    q.push({i, j});
    int cnt = 0;
    visited[i][j] = true;

    while (q.size()) {
        auto cur = q.front();
        q.pop();
        int cr = cur.first;
        int cc = cur.second;
        cnt++;
        for (auto d : dir) {
            int nr = cr + d[0];
            int nc = cc + d[1];
            if (isInRange(nr, nc) && !visited[nr][nc]) {
                visited[nr][nc] = true;
                q.push({nr, nc});
            }
        }
    }

    return cnt;
}
void print() {
    for(int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (visited[i][j]) cout << 1 << " ";
            else cout << 0 << " ";
        }
        cout << "\n";
    }
}
int main(){
    cin >> m >> n >> k;
    for (int i = 0; i < k; i++) {
        cin >> lx >> ly >> rx >> ry;
        for (int r = ly; r < ry; r++) {
            for (int c = lx; c < rx; c++) {
                visited[r][c] = true;
            }
        }
    }

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (!visited[i][j]) {
                area.push_back(bfs(i, j));
            }
        }
    }
    sort(area.begin(), area.end());

    result+=to_string(area.size());
    result+="\n";

    for (int i : area) {
        result+= to_string(i);
        result+=" ";
    }

    cout << result;

    return 0;
}