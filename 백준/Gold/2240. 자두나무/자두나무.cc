#include <bits/stdc++.h>
using namespace std;
int t, w;
int f[1001];
int dp[31][2][1001];
int go(int pos, int idx, int move) {
    if (idx == t) return 0;

    int & ret = dp[move][pos][idx];
    if(ret != -1) return ret;
    ret = (f[idx] == pos) + go(pos, idx + 1, move);
    if (move > 0) {
        ret = max(ret, (f[idx] == (pos + 1) % 2) + go((pos + 1) % 2, idx + 1, move - 1));
    }
    return ret;
}
int main() {
    cin >> t >> w;
    for (int i = 0; i < t; i++) {
        int p;
        cin >> p;
        f[i] = p - 1;
    }

    memset(dp, -1, sizeof(dp));
    cout << go(0, 0, w) << '\n';
    return 0;
}