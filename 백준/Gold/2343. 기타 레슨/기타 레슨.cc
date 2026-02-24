#include <bits/stdc++.h>
using namespace std;
int n, m;
int a[100001];
bool possible(int len) {
    int cnt = 1;
    int sum = 0;
    for (int i = 0; i < n; i++) {
        if (sum + a[i] <= len) {
            sum+=a[i];
            continue;
        }
        sum = a[i];
        cnt++;
    }
    return cnt <= m;
}
int main() {
    cin >> n >> m;
    int l = 0;
    int r = 0;
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        l = max(l, a[i]);
        r+=a[i];
    }
    // 개수 m개, 길이 최소
    while (l < r) {
        int mid = l + (r - l) / 2;
        if (!possible(mid)) {
            l = mid + 1;
        } else {
            r = mid;
        }
    }
    cout << l << '\n';
    return 0;
}