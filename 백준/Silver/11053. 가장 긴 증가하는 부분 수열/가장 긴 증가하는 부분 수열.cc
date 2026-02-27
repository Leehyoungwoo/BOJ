#include <bits/stdc++.h>
using namespace std;
int n;
int a[1001];
int cnt[1001];
int mx;
int main(){
    cin >> n;
    for (int i = 0; i < n; i++) cin >> a[i];

    for (int i = 0; i < n; i++) {
        int maxValue = 0;
        for (int j = 0; j < i; j++) {
            if (a[j] < a[i] && maxValue < cnt[j]) maxValue = cnt[j];
        }
        cnt[i] = maxValue + 1;
        mx = max(mx, cnt[i]);
    }

    cout << mx << '\n';
    return 0;
}