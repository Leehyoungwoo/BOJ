#include <bits/stdc++.h>
using namespace std;
int t, n, m;
set<pair<int, int>> st;
vector<int> adj[1001]; 
bool visited[1001];
bool isCycle = false;
string ret;
// 아 사이클 판별 여부가 아니라 두번 있으면 사이클이 있다고
// 그래서 한 노드에서 다른 모든 노드로 방문이 가능해야 트리고 아니면 그래프라는 얘기네
int bfs(int node) {
    int cnt = 0;
    queue<int> q;
    q.push(node);
    visited[node] = true;
    while (q.size()) {
        int cur = q.front(); q.pop();
        cnt++;
        for (int next : adj[cur]) {
            if (visited[next]) continue;
            q.push(next);
            visited[next] = true;
        }
    }

    return cnt;
}
bool isTree(){
    memset(visited, false, sizeof(visited));

    return n == bfs(n);
}
int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> t;
    for (int tc = 0; tc < t; tc++) {
        cin >> n >> m;
        for (int i = 1; i <= n; i++) adj[i].clear();
        isCycle = false;
        st.clear();
        for (int i = 0; i < m; i++) {
            int u, v;
            cin >> u >> v; 
            adj[u].push_back(v);
            adj[v].push_back(u);   
            if (st.find({u, v}) != st.end()) {
                isCycle = true;   
            }
            st.insert({u, v});
            st.insert({v, u});
        }            
        if (m != n - 1) {
            ret += "graph\n";
            continue;
        }
        if (isCycle) {
            ret+="graph\n";
            continue;
        }
                    // 여기서부터 로직 시작
        if (isTree()) {
            ret+="tree\n";            
        } else {
            ret+="graph\n";
        }
    }
    cout << ret << '\n';
    return 0;
}