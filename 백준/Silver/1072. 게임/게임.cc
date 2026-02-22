#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
ll x, y;
ll winrate;
bool ratechange(int game) {
    ll newrate = (y + game) * 100 / (x + game);

    return winrate != newrate;
}
int main() {
    cin >> x >> y;
    winrate = (y * 100) / x;
    if (winrate >= 99) {
        cout << "-1" << '\n';
        return 0;
    }
    ll l = 0;
    ll r = 1000000000;
    // 최소 몇판 -> 로어바운드
    while (l < r) {
        ll mid = l + (r - l) / 2;
        if (!ratechange(mid)) {
            l = mid + 1;
        } else {
            r = mid;
        }
    }

    cout << l << '\n';
    return 0;
}