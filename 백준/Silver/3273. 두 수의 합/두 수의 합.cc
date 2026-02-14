#include <bits/stdc++.h>
using namespace std;
int n, x;
int a[10000001];
int cnt;
int main() {
    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    cin >> x;
    sort(a, a + n);
    int l = 0;
    int r = n - 1;
    while (l < r) {
        int sum = a[l] + a[r];
        if (sum == x) {
            cnt++;
            l++;
            r--;
        } else if (sum < x) {
            l++;
        } else {
            r--;
        }
    }

    cout << cnt << '\n';
    return 0;
}