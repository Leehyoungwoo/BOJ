#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
int n;
const ll INF = 987654321;
ll w[17][17];
ll dp[17][1 << 17];
ll go(int start, int idx, int mask) {
    if (mask == (1 << n) - 1) {
        if (w[idx][start] == 0) return INF;
        return w[idx][start];
    }

    ll &ret = dp[idx][mask];
    if (ret != -1) return ret;
    ret = INF;
    for (int i = 0; i < n; i++) {
        if (mask & (1 << i)) continue;
        if (w[idx][i] == 0) continue;
        ret = min(ret, go(start, i, mask | (1 << i)) +w[idx][i]);
    }

    return ret;
}
int main(){
    cin >> n;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> w[i][j];
        }
    }
    memset(dp, -1, sizeof(dp));
    cout << go(0, 0, 1) << '\n';
    return 0;
}