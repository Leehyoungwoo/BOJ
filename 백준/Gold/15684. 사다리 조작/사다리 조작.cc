#include <bits/stdc++.h>
using namespace std;
int n, m, h;
bool visited[32][32];
int ret = INT_MAX; 
bool check() {
    for (int i = 1; i <= n; i++) {
        int start = i;
        for (int j = 1; j <= h; j++){
            if (visited[j][start]) start++;
            else if(visited[j][start - 1])start--;
        }
        if (start != i) {
            return false;
        }
    }

    return true;
}
void go (int cur, int cnt) {
    if (cnt > 3 || cnt >= ret) return;
    // 1 - 1 , .... n - n으로 잘 가는지
    if (check()) {
        ret = min(ret, cnt);
        return;
    }
    for (int i = cur; i <= h; i++) {
        for (int j = 1; j < n; j++) {
            if (visited[i][j] || visited[i][j - 1] || visited[i][j + 1]) continue;
            visited[i][j] = 1;
            go(i, cnt + 1);
            visited[i][j] = 0;
        }
    }
}
int main(){
    ios_base::sync_with_stdio(false);
	cin.tie(NULL);
    cout.tie(NULL);
    cin >> n >> m >> h;
    for (int i = 0; i < m; i++) {
        int a, b;
        cin >> a >> b;
        visited[a][b] = 1;
    }
    go(1, 0);
    cout << (ret == INT_MAX ? -1 : ret) << '\n';
    return 0;
}