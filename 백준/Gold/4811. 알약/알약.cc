#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
int t;
int n;
string answer;
// 약 n개
ll dp[31][31];
ll go (int one, int half) {
    if (one < 0 || half < 0) return 0;
    if (one == 0 && half == 0) return 1;

    ll &ret = dp[one][half];
    if (ret != -1) return ret;
    ret = 0;
    ret += go(one, half - 1);
    ret += go(one - 1, half + 1);

    return ret;
}
int main() {
    while(1) {
        cin >> n;
        if (n == 0) break;
        memset(dp, -1, sizeof(dp));
        answer+=to_string(go(n, 0))+ '\n';
    }

    cout << answer << '\n';
    return 0;
}