#include <bits/stdc++.h>
using namespace std;
int n, L, R;
int ret;
int mp[51][51];
bool visited[51][51];
const int dir[4][2] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
vector<int> sz;
vector<vector<pair<int, int>>> v;
// 인구이동은 하루동안 일어나고
// 인구 차이가 L명 이상, R명 이하라면 국경선이 열림
// 국경선이 열렸으면 인구 이동 시작
// 연합 생성
// 연합의 인구수 / 연합을 이루는 칸의 개수 => 연합 인구수 더하고 나눈다음에 분배
bool isInRange(int r, int c) {
    return 0 <= r && r < n && 0 <= c && c < n;
}
int bfs(int r, int c, vector<pair<int, int>> & vec) {
    queue<pair<int, int>> q;
    int sum = 0;
    q.push({r, c});
    visited[r][c] = true;
    while(q.size()) {
        auto cur = q.front();
        q.pop();
        int cr = cur.first;
        int cc = cur.second;
        vec.push_back({cr, cc});
        sum+=mp[cr][cc];
        for (auto d : dir) {
            int nr = cr + d[0];
            int nc = cc + d[1];
            if (!isInRange(nr, nc)) continue; 
            int diff = abs(mp[cr][cc] - mp[nr][nc]);
            if (!visited[nr][nc] && L <= diff && diff <= R) {
                q.push({nr, nc});
                visited[nr][nc] = true;
            }
        }
    }
    
    return sum;
}
void find_union() {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (!visited[i][j]) {
                vector<pair<int, int>> vec;
                int area = bfs(i, j, vec);
                // 한개는 연합이 아님
                if ((int) vec.size() > 1) {
                    sz.push_back(area);
                    v.push_back(vec);
                }
            }
        }
    }
}
void split_people(vector<pair<int, int>> &v, int sum) {
    int avg = sum / (int)v.size();
    for (auto it : v) {
        mp[it.first][it.second] = avg;
    }
}
int main() {
    cin >> n >> L >> R;
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    for (int i = 0; i < n; i++){
        for (int j = 0; j < n; j++) {
            cin >> mp[i][j];
        }
    }
    // 먼저 연합을 찾고 -> 완전탐색하면서 연합을 찾고 인덱스 어딘가에 넣어주기? sum도 구하면 좋고
    // 한번에 이동 -> 연합 별로 인구 분배하기 -> 이전 연합의 인구 분배가 다음 연합의 인구 분배에 영향을 주면 안됨
    // 반복
    while (true) {
        sz.clear();
        v.clear();
        memset(visited, 0, sizeof(visited));
        // 연합 찾기
        find_union();
        // 연합 사이즈가 0이면 (연합이 한개도 없으면 종료)
        // sum계산도 미리 해놓자
        if (v.size() == 0) {
            break;
        }

        // 인구 분배
        for (int i = 0; i < (int)v.size(); i++) {
            vector<pair<int, int>> q = v[i];
            split_people(q, sz[i]);
        }
        ret++;
    }
    cout << ret << "\n";
    return 0;
}