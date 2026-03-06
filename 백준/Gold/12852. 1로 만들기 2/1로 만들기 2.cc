#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
int n;
ll dp[1000001];
int path[1000001];
ll go(int cur) {
    if (cur == 1) return 0;

    ll &ret = dp[cur];
    if (ret != -1) return ret;

    ret = go(cur - 1) + 1;
    path[cur] = cur - 1;

    if (cur % 2 == 0) {
        int cand = go(cur / 2) + 1;
        if (cand < ret) {
            ret = cand;
            path[cur] = cur / 2;
        }
    }

    if (cur % 3 == 0) {
        int cand = go(cur / 3) + 1;
        if (cand < ret) {
            ret = cand;
            path[cur] = cur / 3;
        }
    }

    return ret;
}
int main(){
    cin >> n;
    fill(dp, dp + 1000001, -1);
    cout << go(n) << '\n';
    int cur = n;
    while (cur != 1) {
        cout << cur << ' ';
        cur = path[cur];
    }
    cout << '1' << '\n';
    return 0;
}