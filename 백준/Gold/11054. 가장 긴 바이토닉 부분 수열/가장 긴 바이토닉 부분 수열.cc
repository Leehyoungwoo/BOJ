#include <bits/stdc++.h>
using namespace std;
int n;
int a[1001];
int lis[1001];
int dis[1001];
int ret;
int main() {
    cin >> n;
    for (int i = 0; i < n; i++) cin >> a[i];
    for (int i = 0; i < n; i++) {
        int mx = 0;
        for (int j = 0; j < i; j++) {
            if (a[j] < a[i]) {
                mx = max(mx, lis[j]);
            }
        }
        lis[i] = mx + 1;
    }

    for (int i  = n - 1; i >= 0; i--) {
        int mx = 0;
        for (int j = n - 1; j > i; j--) {
            if (a[j] < a[i]) {
                mx = max(mx, dis[j]);
            }
        }
        dis[i] = mx + 1;
    }
    for (int i = 0; i < n; i++) {
        ret = max(ret, lis[i] + dis[i] - 1);
    }

    cout << ret << '\n';

    return 0;
}