#include <bits/stdc++.h>
using namespace std;
int n;
int k;
int dp[100001];
int ret = 0;
pair<int, int> va[101];
int main() {
    cin >> n >> k;
    for (int i = 0; i < n; i++) {
        int w, v;
        cin >> w >> v;
        va[i] = {w, v};
    }
    for (int i = 0; i < n; i++) {
        int w = va[i].first;
        int v = va[i].second;
        for (int j = k; j >= w; j--) {
            if (j - w < 0) continue;
            dp[j] = max(dp[j], dp[j - w] + v);
            ret = max(ret, dp[j]);
        }
    }

    cout << dp[k] << '\n';
    return 0;
}