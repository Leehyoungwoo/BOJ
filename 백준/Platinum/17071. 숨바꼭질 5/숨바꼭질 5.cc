#include <bits/stdc++.h>
using namespace std;
bool visited[2][500001];
int n, k;
int turn = 1;
bool ok;
int main() {
    cin >> n >> k;
    if (n == k) {
        cout << 0 << '\n';
        return 0;
    }
    queue<int> q;
    q.push(n);
    visited[0][n] = true;
    while (q.size()) {
        k+=turn;
        // 찾는 위치가 500,000을 넘는 경우에는 -1
        if (k > 500000) {
            break;
        }
        if (visited[turn % 2][k]) {
            ok = true;
            break;
        }
        int sz = q.size();
        for (int i = 0; i < sz; i++) {
            int cur = q.front(); q.pop();
            for (int next : {cur + 1, cur - 1, cur * 2}) {
                if (0 <= next && next <= 500001 && !visited[turn % 2][next]) {
                    if (next == k) {
                        ok = true;
                        break;
                    }
                    visited[turn % 2][next] = true;
                    q.push(next);
                }
            }
            if (ok) break;
        }
        if (ok) break;
        turn++;
    }

    if (ok) cout << turn << '\n';
    else cout << -1 << '\n';
    return 0;
}