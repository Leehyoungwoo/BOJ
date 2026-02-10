#include <bits/stdc++.h>
using namespace std;
int n, m;
int a[11];
int mn = INT_MAX;
vector<int> adj[11];
// 선택한거 vs 선택 안한거 비교하면 되지 않을까?
// 그리고 선택한거끼리 다 연결되어있어야하고 안선태된것도 모두 연결되어있어야하는거 아닌가?
// 일단 0 ~ 1<<n - 1 이렇게 하면 9개 선택 / n개 선택이니까 1 ~ 1<<n - 2해야 하는거 아닌가?
// 맞는거 같음 일단은 
// 그러면 mask (특정 몇개 켜져 있는거) ~mask하면 나머지를 켤 수 있지 않나?
// 그리고나서 모두 연결되어있는지 체크하고 되어있으면 합 반환
// 그리고나서 diff min 갱신 아닌가
// 연결 여부는 어떻게 알지? -> 두개로 나뉘니까 같은 영역인 어딘가와는 반드시 연결되어있어야함
bool valid(int mask) {
    int start = -1;
    for (int i = 0; i < n; i++) {
        if (mask & (1 << i)) {
            start = i; break;
        }
    }

    queue<int> q;
    int vis = 0;
    vis |= (1 << start);
    q.push(start);

    int cnt = 0;
    while (q.size()) {
        cnt++;
        int cur = q.front(); q.pop();
        for (int nxt : adj[cur]) {
            if (!(mask & (1 << nxt))) continue;
            if (vis & (1 << nxt)) {
                continue;
            }
            vis |= (1 << nxt);
            q.push(nxt);
        }
    }
    int need = 0;
    for (int i = 0; i < n; i++) if (mask & (1 << i)) need++;

    return cnt == need;
}
int people(int mask) {
    int sum = 0;
    for(int i = 0; i < n; i++){
        if (mask & (1 << i)) sum+=a[i];
    }

    return sum;
}
int main() {
    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    for (int i = 0; i < n; i++){
        cin >> m;
        for (int j = 0; j < m; j++){
            int b;
            cin >> b;
            adj[i].push_back(b - 1);
        }
    }

    for (int i = 1; i <= (1 << n) - 2; i++) {
        int amask = i;
        int bmask = ~i;
        for (int j = 0; j < n; j++) {
            if (valid(amask) && valid(bmask)) {
                int diff = abs(people(amask) - people(bmask));
                mn = min(diff, mn);
            }
        }
    }
    if (mn == INT_MAX) {
        cout << -1 << '\n';
        return 0;
    }
    cout << mn << '\n';
    return 0;
}