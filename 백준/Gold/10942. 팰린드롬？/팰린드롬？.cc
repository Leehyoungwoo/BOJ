#include <bits/stdc++.h>
using namespace std;
int n, m;
int a[2001];
int dp[2001][2001];
int s, e;
int go(int l, int r) {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    if (l >= r) return 1;

    int & ret = dp[l][r];
    if (ret != -1) return ret;
    ret = 0;
    if (a[l] == a[r]) {
        ret = go(l + 1, r - 1);
    }

    return ret;
}
int main(){
    cin >> n;
    for (int i = 0; i < n; i++) cin >> a[i];
    cin >> m;
    memset(dp, -1, sizeof(dp));
    for (int i = 0; i < m; i++) {
        cin >> s >> e;
        s--;
        e--;
        cout << go(s, e) << '\n';
    }

    return 0;
}