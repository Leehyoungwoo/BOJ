#include <bits/stdc++.h>
using namespace std;
// 씨앗은 세개
// 대여는 씨앗 위치 + 사방 
// 꽃끼리 겹치면 안됨
// 최소비용은?
// n은 100이 맥스 100 99 98 / 3 2 1 => 50 * 33 * 98 = 165000
// 테두리는 어차피 안되지 않나? => 2n + 2(n - 2)개 뺼 수 있는거 아닌가? 
int n;
int mp[101][101];
bool visited[101][101];
vector<vector<int>> c;
vector<vector<int>> v;
const int dir[4][2] = {{0, 1}, {0, -1}, {1, 0}, {-1 ,0}};
int mi = INT_MAX;
void print(vector<int> vec) {
    for (int i : vec) cout << i << " ";
    cout << "\n";
}
void combi(int start, vector<int> vec) {
    if (vec.size() == 3) {
        c.push_back(vec);
        return;
    }

    for (int i = start + 1; i <(int) v.size(); i++) {
        vec.push_back(i);
        combi(i, vec);
        vec.pop_back();
    }
}
int main() {
    cin >> n;
    for (int i = 0; i < n; i++){
        for (int j = 0; j < n; j++) {
            cin >> mp[i][j];
            if (i == 0 || i == n - 1 || j == 0 || j == n - 1) {
                continue;
            }
            v.push_back({i, j});
        }
    }
    // 씨앗 세개 심기 조합
    vector<int> vec;
    combi(-1, vec);

    // 만들어놓은 조합 돌리기
    for (auto it : c) {
        memset(visited, false, sizeof(visited));
        bool possible = true;
        int sum = 0;
        for (int idx : it) {
            // 씨앗을 심을 위치
            int cr = v[idx][0];
            int cc = v[idx][1];
            // 땅값
            sum+=mp[cr][cc];
            visited[cr][cc] = true;
            for (auto d : dir) {
                // 사방 + 현재 위치
                int nr = cr + d[0];
                int nc = cc + d[1];
                // 방문한적 있으면 (겹치면) 안됨
                if (visited[nr][nc]) {
                    possible = false;
                    break;
                }
                // 안겹치면 다 더해주기
                sum+=mp[nr][nc];
                visited[nr][nc] = true;
            }
            // 만약 겹치면 그냥 다른 조합 보기
            if (!possible) {
                break;
            }
        }
        // 안겹치면 flag true, 최솟값 갱신
        if (possible) {
            mi = min(mi, sum);
        }
    }

    cout << mi << "\n";
    return 0;
}