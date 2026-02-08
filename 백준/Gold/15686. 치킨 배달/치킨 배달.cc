#include <bits/stdc++.h>
using namespace std;
int n, m;
int mp[53][53];
// 치킨 거리는 집에서 제일 가까운 치킨집 사이의 거리
// 도시의 치킨 거리는 모든 집의 치킨 거리의 합
// 가장 수익을 많이 내는 치킨집의 개수는 m
// 최대 m개를 고르고 나머지는 폐업 => 도시 치킨 거리가 가장 작게 될까?
// m개를 고른다 => 전체 - m개를 폐업한다 => 전체에서 sz개 조합을 구한다
int sz;
int mi = INT_MAX;
vector<pair<int, int>> c;
vector<pair<int, int>> h;
bool visited[53][53];
const int dir[4][2] = {{1, 0}, {-1, 0}, {0, 1},{0, -1}};
bool isInRange(int r, int c) {
    return 0<= r && r < n && 0 <= c && c < n;
}
int bfs(int r, int c) {
    queue<tuple<int, int, int>> q;
    q.push({r, c, 0});
    visited[r][c] =true;
    while (q.size()) {
        auto cur = q.front();
        q.pop();
        int cr = get<0>(cur);
        int cc = get<1>(cur);
        if (mp[cr][cc] == 2) {
            return get<2>(cur);
        }
        for (auto d : dir) {
            int nr = cr+d[0];
            int nc = cc + d[1];
            if (isInRange(nr, nc) && !visited[nr][nc]) {
                q.push({nr, nc, get<2>(cur) + 1});
                visited[nr][nc] = true;
            }
        }
    }

    return 0;
}
void combi(int start, vector<int> & v) {
    if (v.size() == sz) {
        // for (int i : v) {
        //     cout << i << " ";
        // }
        // cout << "\n";
        // v에 폐업할 인덱스 조합이 들어있으니까 순회하면서 폐업시키고(0으로 만듬)
        for (int i : v) {
            auto rm = c[i];
            mp[rm.first][rm.second] = 0;
        }
        // 치킨 거리 구하고
        // bfs하면 시간 초과
        // m은 최대 13보다 작거나 같음 최대 13개
        // 그냥 거리 계산하면?
        int dis = 0; 
        for (auto i : h) {
            int min_dis = INT_MAX;
            for (int j = 0; j < (int)c.size(); j++) {
                // 폐업 안한 치킨집만 돌려보기;
                bool flag = false;
                for (int close : v) {
                    if (j == close) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    continue;
                }
                int ft = abs(i.first - c[j].first) + abs(i.second - c[j].second);
                min_dis = min(min_dis, ft);
            }
            dis+=min_dis;
        }
        mi = min(mi, dis);
        // 원복
        for (int i : v) {
            auto rm = c[i];
            mp[rm.first][rm.second] = 2;
        }

        return;
    }
    for (int i = start + 1; i <(int) c.size(); i++) {
        v.push_back(i);
        combi(i, v);
        v.pop_back();
    }
}
int main(){
    cin >> n >> m;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++){
            cin >> mp[i][j];
            if (mp[i][j] == 2) {
                c.push_back({i, j});
            }
            if (mp[i][j] == 1) {
                h.push_back({i, j});
            }
        }
    }
    sz = (int) c.size() - m;
    // cout << "폐업할 가게 수  : "<< sz << "\n";
    // cout << "치킨집 가게 수 : " << (int) c.size() <<"\n";
    vector<int> v;
    combi(-1, v);
    cout << mi << "\n";
    return 0;
}