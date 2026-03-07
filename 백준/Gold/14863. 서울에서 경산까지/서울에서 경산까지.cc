#include <bits/stdc++.h>
using namespace std;
int n, k;
int INF = -987654321;
vector<int> v[101];
int dp[101][100001];
int go(int idx, int timeLeft) {
    if (timeLeft < 0) return INF;
    if (idx == n) return 0;

    int &ret = dp[idx][timeLeft];
    if (ret != -1) return ret;
    ret = max(
        go(idx + 1, timeLeft - v[idx][0]) + v[idx][1],
        go(idx + 1, timeLeft - v[idx][2]) + v[idx][3]
    );

    return ret;
}
int main() {
    cin >> n >> k;
    int w, m, w1, m1;
    for (int i = 0; i < n; i++) {
        cin >> w >> m >> w1 >> m1;
        v[i] = {w, m, w1, m1};
    }
    memset(dp, -1, sizeof(dp));
    // 0 ~~ n - 1;
    cout << go(0, k) << '\n';
    return 0;
}