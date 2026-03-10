#include <bits/stdc++.h>
using namespace std;
int n;
int l[21];
int j[21];
int dp[21][101];
int hp = 100;
int go(int idx, int hp) {
    if (hp <= 0) return -1e9;
    if (idx == n) return 0;
    
    int &ret = dp[idx][hp];
    if (ret != -1) return ret;
    ret = go(idx + 1, hp);
    if (hp - l[idx] > 0) ret = max(ret , go(idx + 1, hp - l[idx]) + j[idx]);

    return ret;
}
int main(){
    cin >> n;
    for(int i = 0; i < n; i++) cin >> l[i];

    for (int i = 0; i < n; i++) cin >> j[i];

    memset(dp, -1, sizeof(dp));
    cout << go(0, 100) << '\n';
    return 0;
}