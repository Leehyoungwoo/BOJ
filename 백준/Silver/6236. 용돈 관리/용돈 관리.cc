#include <bits/stdc++.h>
using namespace std;
int n, m;
int pay[100001];
bool canM(int k) {
    int money = 0;
    int cnt = 0;
    for (int i = 0; i < n; i++) {
        int need = pay[i];
        // 돈이 남으면 그냥 쓰면 되고
        if (money >= need) {
            money -= need;
            continue;
        }
        // 모자르면 넣고 다시 인출해서 사용
        money = k;
        cnt++;
        money-=need;
    }

    return cnt <= m;
}
int main() {
    int l = 0;
    cin >> n >> m;
    for (int i = 0; i < n; i++) {
        cin >> pay[i];
        l = max(l, pay[i]);
    }
    int r = 2000000000;
    while (l < r) {
        int mid = l + (r - l) / 2;
        if (!canM(mid)) {
            l = mid + 1;
        } else {
            r = mid;
        }
    }
    cout << l << '\n';
    return 0;
}