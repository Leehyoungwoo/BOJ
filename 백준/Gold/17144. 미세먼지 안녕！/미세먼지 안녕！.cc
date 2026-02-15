#include <bits/stdc++.h>
using namespace std;
int n, m, t;
int mp[51][51];
int visited[51][51];
int air_con[2][2];
const int dir[4][2] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
int wind1[4][2] = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
int wind2[4][2] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
queue<pair<int, int>> dust;
void print(){
    for (int i = 0; i < 
        n; i++) {
        for (int j = 0; j < m; j++) {
            cout << mp[i][j] << " ";
        }
        cout << '\n';
    }
}
bool in(int r, int c) {
    return 0 <= r && r < n && 0 <= c && c < m;
}
void go(int r, int c, const int w[4][2]) {
    // r,c에서 출발해서 가는거지
    int prev = 0;
    int d = 0;
    int change = 0;
    while (change < 4) {
        int nr = r + w[d][0];
        int nc = c + w[d][1];
        // 벽이면 방향 전환
        if (!in(nr, nc) || mp[nr][nc] == -1) {
            d = (d + 1) % 4;
            change++;
            nr = r + w[d][0];
            nc = c + w[d][1];
        }
        // 방향전환 4번 했으면 공기 청정기 만나겠지
        // 마지막 칸임
        if (change == 4) {
            // 이전 값 넣어주고 종료
            mp[r][c] = prev;
            break;
        }
        // 공기청정기에 다다르면 바꾸지말고 넘어가자 
        if (mp[r][c] == -1) {
            r = nr;
            c = nc;
            continue;
        }
        // 지금 값에 이전값 씌워주고 이동
        int temp = mp[r][c];
        mp[r][c] = prev;
        prev = temp;
        r= nr;
        c= nc;
    }
}

int main() {
    cin >> n >> m >> t;
    int cnt = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> mp[i][j];
            if (mp[i][j] == -1) {
                air_con[cnt][0] = i;
                air_con[cnt][1] = j;
                cnt++;
            }
            if (mp[i][j] > 0) {
                dust.push({i, j});
            }
        }
    }
    // t초 지나면?
    while (t--) {
        memset(visited, 0, sizeof(visited));
        int sz = (int) dust.size();
        for (int i = 0; i < sz; i++) {
            auto it = dust.front();
            dust.pop();
            int cr = it.first;
            int cc = it.second;
            int c = 0;
            if (mp[cr][cc] / 4 == 0) {
                visited[cr][cc] += mp[cr][cc]; 
                continue;
            } 
            for (auto d : dir) {
                int nr = cr + d[0];
                int nc = cc + d[1];
                if (!in(nr, nc)) continue;
                if (mp[nr][nc] == -1) continue;
                c++;
                visited[nr][nc] += mp[cr][cc] / 5;
            }
            visited[cr][cc] += mp[cr][cc] -  c * (mp[cr][cc] / 5);
        }
        // 이동한 먼지 반영
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] != 0) {
                    mp[i][j] = visited[i][j];
                }
            }
        }
        // 공기청정기 작동
        for (int i = 0; i < cnt; i++) {
            auto aircon = air_con[i];
            int r = aircon[0];
            int c = aircon[1];
            // cout << "공청기 : " << r << " " << c << '\n';
            if (i == 0) {
                go(r, c, wind1);
            } else {
                go(r, c, wind2);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mp[i][j] > 0) {
                    dust.push({i, j});
                }
            }
        }
    }

    int sum = 0;
    // 남아있는 먼지는?
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (mp[i][j] > 0) {
                sum+=mp[i][j];
            }
        }
    }

    cout << sum << '\n';
    return 0;
}