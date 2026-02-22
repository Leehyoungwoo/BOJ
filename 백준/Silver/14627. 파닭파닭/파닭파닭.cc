#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
int s, c;
int par[1000001];
ll total;
// 남은 파를 라면
// 라면에 넣을 파의 양
ll ret;
ll divide(int target) {
    ll cnt = 0;
    for (int i = 0; i < s; i++) {
        cnt+=par[i]/target;
    }
    return cnt;
}
int main(){
    int r = 0;
    cin >> s >> c;
    for (int i = 0; i < s; i++) {
        cin >> par[i];
        r = max(r, par[i]);
        total+=par[i];
    }
    int l = 1;
    int ans = 0;
    while (l <= r) {
        int mid = (ll)l + (r - l) / 2;
        ll cnt = divide(mid);
        if (cnt >= c) {
            ans = mid;
            l = mid + 1;
        } else {
            r = mid - 1;
        }
    }
    ret = total - 1ll * c * ans;
    cout << ret << '\n';
    return 0;
}