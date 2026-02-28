#include <bits/stdc++.h>
using namespace std;
int n;
int a[501];
int cnt[501];
int ret;
int main(){
    cin >> n;
    for (int i = 0; i < n; i++) {
        int idx, h;
        cin >> idx >> h;
        a[idx] = h;
    }
    // 교차되지 않는다 -> end 위치 기준으로 lis면 교차가 안됨 -> 전체 -lis인듯?
    for (int i = 0; i < 502; i++) {
        int maxvalue = 0;
        if (a[i] == 0) continue;
        for (int j = 0; j < i; j++) {
            if (a[j] == 0) continue;
            if (a[j] < a[i] && maxvalue < cnt[j]) {
                maxvalue = cnt[j];
            }
        }
        cnt[i] = maxvalue + 1;
        ret = max(ret, cnt[i]);
    }

    cout << n - ret << '\n';
    return 0;
}