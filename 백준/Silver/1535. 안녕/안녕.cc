#include <bits/stdc++.h>
using namespace std;
int n;
int hp[101];
int happy[101];
int dp[101][20];
int INF = 1e9;
int go(int h, int idx) {
    if (h <= 0) return -INF;
    if (idx == n) return 0;

    int & ret = dp[h][idx];
    if (ret != -1) return ret;
    ret = 0;
    ret = max(go(h - hp[idx],idx + 1) + happy[idx], go(h, idx + 1));

    return ret;
}
int main(){
    cin >> n;
    for (int i = 0; i < n; i++) cin >> hp[i];
    for (int i = 0; i < n; i++) cin >> happy[i];

    memset(dp, -1, sizeof(dp));
    cout << go(100, 0) << '\n';
    return 0;
}