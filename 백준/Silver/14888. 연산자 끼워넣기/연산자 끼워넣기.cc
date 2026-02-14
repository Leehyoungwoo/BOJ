#include <bits/stdc++.h>
using namespace std;
int n;
int a[101];
int op[4];
int mx = INT_MIN;
int mn = INT_MAX;
// 덧셈, 뺄셈, 곱셉, 나눗셈
void go(int idx, int ret) {
    // cout << "인덱스 : " << idx << '\n'; 
    // cout << "누적 : " << ret << '\n'; 
    if (idx == n) {
        mx = max(mx, ret);
        mn = min(mn, ret);
        return;
    }
    for (int i = 0; i < 4; i++) {
        if (op[i] == 0) continue;
        op[i]--;
        if (i == 0) {
            go(idx + 1, ret + a[idx]);
        } else if (i == 1) {
            go(idx + 1, ret - a[idx]);
        } else if (i == 2) {
            go(idx +1, ret * a[idx]); 
        } else {
            go(idx + 1, ret / a[idx]);
        }
        op[i]++;
    }
}
int main(){
    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    for (int i = 0; i < 4; i++) {
        cin >> op[i];
    }

    go(1, a[0]);
    cout << mx << '\n' << mn << '\n';
    return 0;
}