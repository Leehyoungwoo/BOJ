#include <bits/stdc++.h>
using namespace std;
int n, m, t;
int mp[51][51];
bool visited[51][51];
int x, d, k;
int ret;
bool er;
const int dir[4][2] = {{0,1}, {0, -1}, {1,0}, {-1, 0}};
void print() {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cout << mp[i][j] << " ";
        }
        cout << '\n';
    }
    cout << '\n';
}
void erase() {
    er = true;
    memset(visited, false, sizeof(visited));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            int r = i;
            int c = j;
            if (mp[r][c] == 0) continue;
            for (const auto& d : dir) {
                int nr = r + d[0];
                int nc = (c + m + d[1]) % m;
                if (!(0 <= nr && nr < n)) continue;
                if (mp[r][c] == mp[nr][nc]) {
                    visited[r][c] = true;
                    visited[nr][nc] = true;
                }
            }
        }
    }
    int cnt = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (visited[i][j]) mp[i][j] = 0, cnt++;
        }
    }
    if (!cnt) {
        er = false;
    }
}
void move(int target) {
    if (d == 0) {
        int prev = mp[target][0];
        for (int i = 1; i < m; i++) {
            int temp = mp[target][i];
            mp[target][i] = prev;
            prev = temp;
        }
        mp[target][0] = prev;
    } else {
        int prev = mp[target][m - 1];
        for (int i = m - 2; i >= 0; i--) {
            int temp = mp[target][i];
            mp[target][i] = prev;
            prev = temp;
        }
        mp[target][m - 1] = prev;
    }
}
void turn() {
    for (int i = 0; i < n; i++) {
        if ((i + 1) % x == 0) {
            for (int j = 0; j < k; j++) {
                move(i);
            }
        }
    }
}
void adjust(){
    int cnt = 0;
    int sum = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (mp[i][j] != 0) {
                cnt++, sum+=mp[i][j];
            }
        }
    }
    if (cnt == 0) return;
    double avg = (double)sum / cnt;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if(!mp[i][j]) continue;
            if (mp[i][j] > avg) {
                mp[i][j]--;
            } else if (mp[i][j] < avg){
                mp[i][j]++;
            }
        }
    }
}
int main(){
    cin >> n >> m >> t;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> mp[i][j];
        }
    }
    for (int i =0; i < t; i++) {
        cin >> x >> d >> k;
        turn();
        erase();
        if (!er) {
            adjust();
        }
    }
    for (int i = 0 ; i < n; i++) {
        for (int j = 0; j < m; j++) {
            ret+=mp[i][j];
        }
    }
    cout << ret << '\n';
    return 0;
}