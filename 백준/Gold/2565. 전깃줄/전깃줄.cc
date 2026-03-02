#include <bits/stdc++.h>
using namespace std;
int n;
int a[501];
int cnt[501];
int ret;
int main() {
    cin >> n;
    for (int i = 0; i < n; i++) {
        int idx, end;
        cin >> idx >> end;
        a[idx] = end;
    }

    for(int i = 0; i < 501; i++) {
        int maxValue = 0;
        if (a[i] == 0) continue;
        for (int j = 0; j < i; j++) {
            if (a[j] == 0) continue;
            if (a[j] < a[i] && maxValue < cnt[j]) {
                maxValue = cnt[j];
            }
        }
        cnt[i] = maxValue + 1;
        ret = max(ret, cnt[i]);
    }

    cout << n - ret << '\n';
    return 0;
}