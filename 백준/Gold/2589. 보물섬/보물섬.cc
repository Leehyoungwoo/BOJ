#include <bits/stdc++.h>
using namespace std;
int n, m;
char mp[51][51];
bool visited[51][51];
//  같은 곳을 두 번 이상 지나가거나, 멀리 돌아가서는 안됨 -> 지나간곳 다시 못지나감
// 육지만 이동 가능  -> 육지 L, 바다 W
// 보물은 두 곳에 묻혀 있는데 최단 거리로 이동하는 가장 먼 곳에 묻혀있다네
// land는 많아봐야 2500개, 각 bfs로 이동해봐야 최대로 2500개 아닌가 => 7250000
int mx = INT_MIN;
vector<pair<int, int>> l;
const int dir[4][2] ={{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
bool isInRange(int r, int c) {
    return 0 <=r && r < n && 0 <= c && c < m;
}
int bfs(int r, int c) {
    int dis = 0;
    queue<tuple<int, int, int>> q;
    visited[r][c] = true;
    q.push({r, c, 0});
    while (q.size()) {
        auto cur = q.front();
        q.pop();
        int cr = get<0>(cur);
        int cc = get<1>(cur);
        int cd = get<2>(cur);
        dis = max(dis, cd);
        for (auto d : dir) {
            int nr = cr + d[0];
            int nc = cc + d[1];
            if (isInRange(nr, nc) && !visited[nr][nc] && mp[nr][nc] == 'L') {
                q.push({nr, nc, cd + 1});
                visited[nr][nc] = true;
            }
        }
    }

    return dis;
}
int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);
    cin >> n >> m;
    for (int i = 0; i < n; i++) {
        string s;
        cin >> s;
        for (int j = 0; j < m; j++){
            mp[i][j] = s[j];
            if (mp[i][j] == 'L') {
                l.push_back({i, j});
            }
        }
    }

    // land에서 bfs해서 최대 길이 return 해주면 되지 않나? 그리고 그거 max로 갱신해주면 될거 같은데 
    for (auto it : l) {
        memset(visited, 0, sizeof(visited));
        int dis = bfs(it.first, it.second);
        mx = max(mx, dis);
    }

    cout << mx << "\n";
    return 0;
}