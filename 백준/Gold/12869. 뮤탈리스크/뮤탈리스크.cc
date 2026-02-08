#include <bits/stdc++.h>
using namespace std;
int a[3];
int n;
int dir[6][3] ={{9, 3, 1}, {9, 1, 3}, {3, 1, 9}, {3, 9, 1}, {1,3,9},{1,9, 3}};
int visited[61][61][61];
struct A{
    int a, b, c;
};
int solve(int a, int b, int c) {
    queue<A> q;
    q.push({a, b, c});
    visited[a][b][c] = 1;
    while (q.size()) {
        auto cur = q.front();
        int a = cur.a;
        int b = cur.b;
        int c = cur.c;
        q.pop();
        for (auto d : dir) {
            int na = max(0, a - d[0]);
            int nb = max(0, b - d[1]);
            int nc = max(0, c - d[2]);
            if (!visited[na][nb][nc]) {
                q.push({na, nb, nc});
                visited[na][nb][nc] = visited[a][b][c] + 1; 
            }
        }
    }

    return visited[0][0][0] - 1;
}
int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    cout << solve(a[0], a[1], a[2]) << "\n";
    return 0;
}