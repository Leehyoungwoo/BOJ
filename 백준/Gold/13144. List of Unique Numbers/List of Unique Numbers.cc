#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
int n;
int a[100001];
int visited[100001];
ll ret;
int main() {
    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    fill(visited, visited + 100001, -1);
    int l = 0;
    int r = 0;
    while (r < n) {
        if (visited[a[r]] == -1) {
            visited[a[r]] = r;
        } else {
            l = max(l, visited[a[r]] + 1);
            visited[a[r]] = r;
        }
        ret += r - l + 1;
        r++;
    }

    cout << ret << '\n';
    return 0;
}