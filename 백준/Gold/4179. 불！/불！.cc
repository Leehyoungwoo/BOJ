#include <bits/stdc++.h>
using namespace std;
int n, m;
// 100만 
char mp[1001][1001];
bool jvisited[1001][1001];
pair<int, int> J;
queue<tuple<int, int, int>> q;
const int dir[4][2] = {{1, 0}, {-1 ,0}, {0, 1}, {0, -1}};
// 미로를 탈출 => 미로의 가장자리에 가면 탈출한거
bool isInRange(int r, int c) {
    return 0 <=r && r < n && 0<= c && c < m;
}
bool escape(int r, int c) {
    return r == 0 || r == n - 1 || c == 0 || c == m - 1;
}
string bfs() {
    // 불을 번지게 하고 지훈을 움직이면 되지 않나? => 큐에 불부터 넣고 그 다음 지훈을 넣자
    // 지훈 다음 무빙에 탈출 못했는데 더이상 갈 곳이 없으면 IMPOSSIBLE, 탈출하면 이태까지 움직인 시간
    int t = 0;
    q.push({J.first, J.second, 0});
    jvisited[J.first][J.second] = true;
    while (q.size()) {
        int sz = (int)q.size();
        for (int i = 0; i < sz; i++) {
            auto cur = q.front();
            q.pop();
            int cr = get<0>(cur);
            int cc = get<1>(cur);
            int ct = get<2>(cur);
            if (ct != -1 && escape(cr, cc)) {
                t = ct;
                return to_string(t + 1);
            }

            for (auto d : dir) {
                int nr = cr + d[0];
                int nc = cc + d[1];
                if (isInRange(nr, nc) && mp[nr][nc] != '#'){
                    if (ct != -1 && !jvisited[nr][nc] && mp[nr][nc] == '.') {
                        q.push({nr, nc, ct + 1});
                        jvisited[nr][nc] = true;
                    } else {
                        // 무한루프 방지
                        if (mp[nr][nc] == 'F') {
                            continue;
                        }
                        q.push({nr, nc, ct});
                        mp[nr][nc] = 'F';
                    }
                }
            }
        }
    }

    return "IMPOSSIBLE";
}
int main(){
    cin >> n >> m;
    for (int i = 0; i < n; i++) {
        string s;
        cin >> s;
        for (int j = 0; j < m; j++){
            mp[i][j] = s[j];
            if (mp[i][j] == 'J') {
                J = {i, j};
            }
            if (mp[i][j] == 'F') {
                q.push({i, j, -1});
            }
        }
    }
    cout << bfs() << "\n";

    return 0;
}