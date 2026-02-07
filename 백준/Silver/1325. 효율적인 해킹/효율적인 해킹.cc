#include <bits/stdc++.h>
using namespace std;
int n, m;
vector<int> adj[10001];
bool visited[10001];
string ret;
vector<int> v;
int mx = INT_MIN;
int dfs(int cur) {
    visited[cur] = true;
    int cnt = 1;
    for (int next : adj[cur]) {
        if (!visited[next]) {
            cnt+=dfs(next);
        }
    }
    
    return cnt;
}
int main() {
    cin >> n >> m;
    for (int i = 0; i < m; i++){
        int u, v;
        cin >> u >> v;
        // u가 v를 신뢰한다 => v를 해킹하면 u도 해킹
        adj[v].push_back(u);
    }

    for (int i = 1; i <= n; i++) {
        memset(visited, 0, sizeof(visited));
        int cnt = dfs(i);
        if (cnt > mx) {
            mx = cnt;
            v.clear();
            v.push_back(i);
        } else if (cnt == mx) {
            v.push_back(i);
        }
    }
    sort(v.begin(), v.end());

    for(int i : v) {
        ret+=to_string(i);
        ret += " ";
    }

    cout << ret;

    return 0;
}