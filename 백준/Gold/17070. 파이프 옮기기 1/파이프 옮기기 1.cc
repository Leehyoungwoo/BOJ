#include <bits/stdc++.h>
using namespace std;
int n;
int mp[17][17];
int dp[17][17][3];
// 파이프 가로로 놓여있으면 2가지, 세로도 두가지, 대각선 3가지 방법 존재 (세방향에서 더해야한다는 얘기네)
bool in(int r, int c) {
    return 0 <= r && r < n && 0 <= c && c < n;
}
int go(int r, int c, int dir) {
    if (!in(r, c)) return 0;
    if (r == n - 1 && c == n - 1) return 1;

    int &ret = dp[r][c][dir];
    if (ret != -1) return ret;
    ret = 0;
    if (dir == 0) {
        if (in(r, c + 1) && mp[r][c + 1] == 0) ret += go(r, c + 1, dir);
        
        if (in(r + 1, c + 1) && in(r, c + 1) && in(r + 1, c) && !mp[r + 1][c + 1] && !mp[r][c + 1] && !mp[r + 1][c]) {
            ret+= go(r + 1, c + 1, 2);
        }
    }

    if (dir == 1) {
        if (in(r + 1, c) && !mp[r + 1][c]) ret+=go(r + 1, c, dir);
        if (in(r + 1, c + 1) && in(r, c + 1) && in(r + 1, c) && !mp[r + 1][c + 1] && !mp[r][c + 1] && !mp[r + 1][c]) {
            ret+= go(r + 1, c + 1, 2);
        }
    }

    if (dir == 2) {
        if (in(r, c + 1) && mp[r][c + 1] == 0) ret += go(r, c + 1, 0);
        if (in(r + 1, c) && !mp[r + 1][c]) ret+=go(r + 1, c, 1);
        if (in(r + 1, c + 1) && in(r, c + 1) && in(r + 1, c) && !mp[r + 1][c + 1] && !mp[r][c + 1] && !mp[r + 1][c]) {
            ret+= go(r + 1, c + 1, dir);
        }
    }

    return ret;
}
int main(){
    cin >> n;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++){
            cin >> mp[i][j];
        }
    }
    memset(dp, -1, sizeof(dp));
    cout << go(0, 1, 0) << '\n';
    return 0;
}