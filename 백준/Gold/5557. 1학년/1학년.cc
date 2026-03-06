#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
int n;
int a[101];
ll dp[101][21];
ll go(int idx, int sum) {
    if (sum < 0 || sum > 20) return 0;
    if (idx == n - 1) return sum == a[n - 1];
    ll &ret = dp[idx][sum];
    if (ret != -1) return ret;

    ret = 0;

    ret += go(idx + 1, sum + a[idx]) + go(idx + 1, sum - a[idx]);

    return ret;
}
int main() {
    memset(dp, -1, sizeof(dp));
    cin >> n;
    for (int i = 0; i < n; i++) cin >> a[i];
    cout << go(1, a[0]) << '\n';
    return 0;
}